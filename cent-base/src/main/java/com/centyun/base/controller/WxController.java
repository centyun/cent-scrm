package com.centyun.base.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.centyun.base.constant.BaseConstant;
import com.centyun.base.constant.WxConstant;
import com.centyun.base.domain.WxAuth;
import com.centyun.base.service.WxService;
import com.centyun.base.util.UrlUtil;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.web.controller.WebBaseController;

@Controller
public class WxController extends WebBaseController {

    private Logger log = LoggerFactory.getLogger(WxController.class);
    
    @Autowired
    private WxService wxService;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${BASE_URL}")
    private String baseUrl;

    @RequestMapping("/wxauth")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));

        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if (user == null) {
            throw new BadRequestException(AppConstant.AUTH_FAIL);
        }
        model.addObject("wxAuth", wxService.getWxAuthByTenantId(user.getTenantId()));
        model.setViewName("wx/wx-index");
        return model;
    }

    @RequestMapping(value = "/wxauth/save-wxauth", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDwz(WxAuth wxAuth, HttpServletRequest request) {
        String id = null;
        try {
            User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
            if (user == null) {
                throw new BadRequestException(AppConstant.AUTH_FAIL);
            }
            id = wxService.saveWxAuth(wxAuth, user);
        } catch (BadRequestException e) {
            log.error(e.getMessage(), e);
            ResultEntity result = new ResultEntity();
            result.setData(getMessage(e.getMessage(), request));
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ResultEntity result = new ResultEntity();
            result.setData(e.getMessage());
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            return result;
        }
        ResultEntity resultEntity = new ResultEntity(HttpStatus.OK.value());
        resultEntity.setData(StringUtils.isEmpty(id) ? "" : id);
        return resultEntity;
    }

    @RequestMapping("/wxauth/auth2/{tenantId}")
    @ResponseBody
    public Object auth2(@PathVariable String tenantId, @RequestParam("redirectUri") String redirectUri, HttpServletRequest request) {
        try {
            WxAuth wxAuth = wxService.getWxAuthByTenantId(tenantId);
            String appId = wxAuth.getAppId();
//            String appSecret = wxAuth.getAppSecret();
            SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(BaseConstant.DATACENTER_ID, BaseConstant.MACHINE_ID);
            String state = "wxstate_" + snowFlake.nextId();
            stringRedisTemplate.opsForValue().set(state, redirectUri, 20, TimeUnit.SECONDS); // 把state和redirectUri建立关系，并保存到redis中20秒

            StringBuffer callbackSb = new StringBuffer(baseUrl);
            callbackSb.append("/wxauth/auth2/").append(tenantId).append("/callBack.html");
            String url = WxConstant.AUTH_CODE_URL.replace("APPID", appId).replace("REDIRECT_URI", callbackSb.toString()).replace("STATE", state);
            return "redirect:" + url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppConstant.ERROR;
    }

    /**
     * 回调(获取用户信息)
     *
     * @param projectType 项目类型
     * @param clientType  客户端类型
     * @param code        微信授权码
     * @return
     */
    @RequestMapping(value = "/wxauth/auth2/{tenantId}/callBack.html")
    public String weixinAuthCallBack(@PathVariable String tenantId, @RequestParam("state") String state, String code) {
        try {
            WxAuth wxAuth = wxService.getWxAuthByTenantId(tenantId);
            String appId = wxAuth.getAppId();
            String appSecret = wxAuth.getAppSecret();
            String openId = getWeixinOpenId(appId, appSecret, code);
            if(StringUtils.isEmpty(openId) || "null".equalsIgnoreCase(openId)){
                openId = "";
                log.warn(">>>> 回调(获取用户信息) appId、appSecret 配置错误, 请检查 appId、appSecret 参数");
            }
            String redirectUri = stringRedisTemplate.opsForValue().get(state); // 根据state从缓存中查出最原始的redirectUri
            redirectUri = UrlUtil.addParameter(redirectUri, "openId", openId);
            return "redirect:" + redirectUri;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return AppConstant.ERROR;
    }

    private String getWeixinOpenId(String appId, String appSecret, String code) {
        try {
            String openId = getOpenIdFromRedis(appId, code); // 如果从redis取到了则直接返回(避免一个code多次调用微信接口获取openId)
            if(!StringUtils.isEmpty(openId)){
                return openId;
            }
            
            openId = getOpenIdFromWxApi(appId, appSecret, code);
            if(!StringUtils.isEmpty(openId)){
                String wxOpenIdKey = "wxopenid_" + appId + "_" + code;
                stringRedisTemplate.opsForValue().set(wxOpenIdKey, openId, 20, TimeUnit.SECONDS); // 如果取到了openId则放到redis中20秒，避免重复请求weix接口
            }
            return openId;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private String getOpenIdFromWxApi(String appId, String appSecret, String code) throws URISyntaxException, IOException {
        Map<String, String> param = new HashMap<>();
        param.put("appid", appId);
        param.put("secret", appSecret);
        param.put("code", code);
        param.put("grant_type", "authorization_code");
        ResponseEntity<String> entity = restTemplate.getForEntity(WxConstant.OPNEID_URL, String.class, param);
        String body = entity.getBody();
        log.debug("调用weixin接口返回openid结果:" + body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        String openId = jsonObject.getString("openid");
        return openId;
    }
    
    private String getOpenIdFromRedis(String appId, String code) {
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(code)) {
            return null;
        }
        String wxOpenIdKey = "wxopenid_" + appId + "_" + code;
        String openId = stringRedisTemplate.opsForValue().get(wxOpenIdKey);
        log.info(wxOpenIdKey + "======通过缓存获取到openId:" + openId);
        return openId;
    }
}

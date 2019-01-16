package com.centyun.console.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.core.captcha.Captcha;
import com.centyun.core.captcha.GifCaptcha;
import com.centyun.core.client.UserFeignClient;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ProductVO;
import com.centyun.core.domain.User;
import com.centyun.core.security.CaptchaAuthenticationFilter;
import com.centyun.core.util.CookieUtils;
import com.centyun.core.util.IOUtils;
import com.centyun.core.util.RandomGenerator;
import com.centyun.core.util.encode.EncryptUtils;
import com.centyun.core.util.encode.Sha256Util;

@Controller
public class ConsoleController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserFeignClient userFeignClient;

    @Value("${HOME_URL}")
    private String homeUrl;

    @RequestMapping(value = {"", "/", "/index.html"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getAvailableProducts(request));
        model.addObject("logoutUrl", "/logout");
        model.setViewName("index");
        return model;
    }

    private List<ProductVO> getAvailableProducts(HttpServletRequest request) {
        int port = request.getServerPort();
        String serverName = request.getServerName();
        String server = (port == 80 || port == 443) ? serverName : serverName + ":" + port;
        List<ProductVO> products = userFeignClient.getAvailableProducts();
        // setActive
        for (ProductVO product : products) {
            String releaseUrl = product.getReleaseUrl();
            if(releaseUrl != null && releaseUrl.length() > 8) { // https:// 的长度是8
                String host = getHost(releaseUrl);
                product.setActive(host.equals(server));
            } else {
                product.setActive(false);
            }
        }
        return products;
    }

    /**
     * 只获取url中域名(或ip:port)的内容
     * @param releaseUrl
     * @return
     */
    private String getHost(String releaseUrl) {
        try {
            URL url = new URL(releaseUrl);
            return url.getHost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 如果通过构造URL解析失败，则通过截取字符串进行比较
        int begin = 7;
        if(releaseUrl.startsWith("https://")) {
            begin = 8; 
        }
        return releaseUrl.substring(begin).split("/")[0];
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, ModelAndView model
            , HttpServletRequest request, HttpServletResponse response) {
        if (error != null) {
            model.addObject("error", "Login.UserPasswdError");
        } else if (logout != null) { // 登出, 删除cookie
            CookieUtils.deleteCookie(request, response, AppConstant.TOKEN);
            model.addObject("msg", "Logout.Success");
        } else {
            Object context = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            if(context != null && context instanceof SecurityContext) {
                SecurityContext securityContext = (SecurityContext) context;
                Authentication authentication = securityContext.getAuthentication();
                if(authentication != null) {
                    try {
                        response.sendRedirect(homeUrl); // 如果已经登录了, 直接跳入到首页
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            //如果没有登录，则获取cookie中的token去查询系统登录情况，如果查到了则赋值然后自动登录，如果没有查到则进入登录界面
            String token = CookieUtils.getCookieValue(request, AppConstant.TOKEN);
            if (!StringUtils.isEmpty(token)) { // cookie中没有token则跳转到登录页
                User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
                if(user == null) {
                    user = userFeignClient.getUserByToken(token);
                    model.addObject("user", user.getUsername());
                    String sha256 = Sha256Util.getSha256(RandomGenerator.getString(12));
                    model.addObject(CaptchaAuthenticationFilter.SESSION_KEY, "ENCD(" + sha256 + ")");
                    request.getSession().setAttribute(CaptchaAuthenticationFilter.SESSION_KEY_ENCODE, EncryptUtils.encrypt(sha256));
                }
            }
        }
        return "login";
    }

    /**
     * 生成验证码
     */
    @RequestMapping("/captcha-image")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream os = null;
        try {
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/gif");
            // gif格式动画验证码, 宽，高，位数。
            Captcha captcha = new GifCaptcha(110, 36, 4);
            os = response.getOutputStream();
            captcha.out(os); // 输出
            // 将验证码存入session
            request.getSession().setAttribute(CaptchaAuthenticationFilter.SESSION_CAPTCHA_KEY,
                    captcha.text().toLowerCase());
        } catch (Exception e) {
            System.err.println("获取验证码异常：" + e.getMessage());
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.close(os);
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // TODO 记录登出成功的日志
        return "redirect:/login?logout";
    }

}

package com.centyun.base.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.base.BaseApplication;
import com.centyun.base.domain.FaceGroup;
import com.centyun.base.service.FaceService;
import com.centyun.base.util.FaceSearch;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.util.file.FileUploadUtils;
import com.centyun.web.controller.WebBaseController;

@Controller
public class FaceController extends WebBaseController {
    
    private Logger log = LoggerFactory.getLogger(FaceController.class);
    
    @Autowired
    private FaceService faceService;
    
    @Value("${baidu.ai.appid}")
    private String appId;

    @Value("${baidu.ai.apikey}")
    private String apikey;

    @Value("${baidu.ai.secretkey}")
    private String secretkey;

    @Value("${UPLOAD_DIR}")
    private String uploadDir;

    @Value("${BASE_URL}")
    private String baseUrl;
    
    private static long lastTime = System.currentTimeMillis();

    @RequestMapping("/face")
    public ModelAndView index(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if (user == null) {
            throw new BadRequestException(AppConstant.AUTH_FAIL);
        }
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
//        model.addObject("wxAuth", wxService.getWxAuthByTenantId(user.getTenantId()));
        model.setViewName("face/face-index");
        return model;
    }

    @RequestMapping("/face/face-user-add")
    public ModelAndView userAdd(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if (user == null) {
            throw new BadRequestException(AppConstant.AUTH_FAIL);
        }
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
        model.setViewName("/face/face-user-add");
        return model;
    }
    
    @RequestMapping("/face/recoginze")
    @ResponseBody
    public Object recoginze(@RequestParam("base64Image") String base64Image, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if (user == null) {
            throw new BadRequestException(AppConstant.AUTH_FAIL);
        }
        ResultEntity search = null;
        
        long now = System.currentTimeMillis();
        // 后台2分钟调用一次接口
        if(now - lastTime > 2000) {
            FaceGroup group = faceService.getGroupByTenantId(user);
            if(group.getStatus() == 0) {// 如果该组在ai中没有创建，则调用ai创建组的接口
                JSONObject res = FaceSearch.createGroup(appId, apikey, secretkey, group.getId());
                int errorCode = res.getInt("error_code");
                if(errorCode == 0) { // 在ai中成功创建组，则更改db中组的状态
                    faceService.updateGroupStatus(user.getTenantId());
                }
            }
            search = FaceSearch.search(base64Image, appId, apikey, secretkey, group.getId());
            log.info("===face recoginze result===" + search);
            System.out.println("===face recoginze result===" + search);
            int status = search.getStatus();
            if(status == HttpStatus.OK.value()) { // 识别成功
                lastTime = now;
            } else {
                search.setData(getMessage("face.recoginze." + search.getData(), request));
            }
        } else {
            search = new ResultEntity(HttpStatus.PROCESSING.value());
            search.setData(getMessage("face.recoginze.processing", request));
        }
        return search;
    }

    @RequestMapping(value = "/face/save-face", method = RequestMethod.POST)
    @ResponseBody
    public Object saveFace(@RequestParam(value="name") String name, @RequestParam(value="face", required=false) String face
            , @RequestParam(value="base64Image", required=false) String base64Image, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if (user == null) {
            throw new BadRequestException(AppConstant.AUTH_FAIL);
        }
        
        try {
            faceService.saveFaceUser(user, name, face, base64Image);
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
        return new ResultEntity(HttpStatus.OK.value());
    }

    /**
     * 上传文件
     * 
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity uploadFile(@RequestParam(value = "file", required = true) MultipartFile file) {
        ResultEntity result = new ResultEntity(HttpStatus.OK.value());
        try {
            File saveFile = FileUploadUtils.saveFile(file, uploadDir, BaseApplication.CODE);
            String fileUrl = baseUrl + AppConstant.UPLOAD_DIR
                    + saveFile.getAbsolutePath().substring(uploadDir.length());
            result.setData(fileUrl);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}

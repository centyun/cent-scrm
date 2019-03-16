package com.centyun.user.controller;

import java.io.File;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.centyun.core.captcha.Captcha;
import com.centyun.core.captcha.GifCaptcha;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.Administrator;
import com.centyun.core.domain.Audit;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.security.CaptchaAuthenticationFilter;
import com.centyun.core.service.AuditService;
import com.centyun.core.util.IOUtils;
import com.centyun.core.util.IpUtils;
import com.centyun.core.util.file.FileUploadUtils;
import com.centyun.user.UserApplication;

@Controller
public class IndexController extends BaseController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuditService auditService;

    @Value("${UPLOAD_DIR}")
    private String uploadDir;

    @Value("${USER_URL}")
    private String userUrl;

    @RequestMapping({"", "/", "/index.html"})
    public String home() {
        return "forward:/tenant";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getMessage("Login.UserPasswdError", request));
        }
        if (logout != null) { // 登出, 删除cookie
//            CookieUtils.deleteCurrentDomainCookie(request, response, AppConstant.TOKEN); 不需要cookie
            model.addObject("msg", getMessage("Logout.Success", request));
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping("/logout")
    public String userLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // TODO 记录登出成功的日志 这里的代码没有起作用??
        Object principal = auth.getPrincipal();
        if (principal instanceof Administrator) {
            Administrator administrator = (Administrator) principal;
            String ip = request.getParameter("ip");
            Audit audit = new Audit();
            audit.setAction("logout");
            audit.setModule("system");
            audit.setContent(ip);
            audit.setIp(IpUtils.ipToLong(ip));
            audit.setOperator(administrator.getId());
            auditService.saveAudit(audit);
        }

        return "redirect:/login?logout";
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

    /**
     * 切换语言
     *
     * @param lang, zh - 中文, en - 英文
     */
    @RequestMapping(value = "/change-language", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity changeLanguage(HttpSession session, String lang) {
        if ("zh_CN".equals(lang)) {
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.CHINA);
        } else if ("en_US".equals(lang)) {
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
        } else {
            String[] langs = lang.split("_");
            Locale locale = new Locale(langs[0], langs[1]);
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
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
            File saveFile = FileUploadUtils.saveFile(file, uploadDir, UserApplication.APPNAME);
            String fileUrl = userUrl + AppConstant.UPLOAD_DIR
                    + saveFile.getAbsolutePath().substring(uploadDir.length());
            result.setData(fileUrl);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseEntity<String> handleException(MaxUploadSizeExceededException ex) {
        log.debug("=====================" + ex.getClass().getName());
        return ResponseEntity.ok("ok");
    }

}

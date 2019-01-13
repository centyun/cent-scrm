package com.centyun.user.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.core.util.CommonUtils;
import com.centyun.core.util.encode.EncryptUtils;
import com.centyun.user.service.TenantService;
import com.centyun.user.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    
    private Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @Autowired
    private TenantService tenantService;

    @RequestMapping(value = "/index.html")
    public ModelAndView index(@RequestParam(required=false) Long tenantId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("tenantId", tenantId == null || tenantId <= 0 ? 0 : tenantId);
        model.addObject("tenants", tenantService.getAllTenants());
        model.setViewName("user/user-index");
        return model;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public Object getUsers(@ModelAttribute DataTableParam dataTableParam, @RequestParam(required=false) Long tenantId) {
        PageInfo<User> tenants = userService.getPageUsers(dataTableParam, tenantId == null || tenantId <= 0 ? null : tenantId);
        return new DataTableResult<User>(tenants.getList(), tenants.getTotal(), dataTableParam.getDraw());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Object getUser(@PathVariable(value = "userId") Long userId) {
        User user = userService.getUserById(userId);
        return user;
    }

    @RequestMapping(value = "/getUserByToken")
    @ResponseBody
    public Object getUserByToken(@RequestParam(value = "token") String token) {
        User user = userService.getUserByToken(token);
        return user;
    }

    @RequestMapping(value = "/add.html")
    public ModelAndView add(@RequestParam(required=false, value="tenantId") String tenantId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("tenants", tenantService.getAllTenants());
        if(!CommonUtils.isEmpty(tenantId)) {
            model.addObject("tenantId", tenantId);
        }
        model.setViewName("user/user-add");
        return model;
    }

    @RequestMapping(value = "/edit.html")
    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        User user = userService.getUserById(id);
        model.addObject("user", user);
        model.setViewName("user/user-edit");
        return model;
    }

    @RequestMapping(value = "/view.html")
    public ModelAndView view(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        User user = userService.getUserById(id);
        model.addObject("user", user);
        model.setViewName("user/user-view");
        return model;
    }

    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    @ResponseBody
    public Object saveUser(User user, HttpServletRequest request) {
        try {
            userService.saveUser(user);
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

    @RequestMapping(value = "/update-language")
    @ResponseBody
    public Object updateLanguage(@RequestParam("id") Long id, @RequestParam("language") String language) {
        try {
            userService.updateLanguage(id, language);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ResultEntity result = new ResultEntity();
            result.setData(e.getMessage());
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            return result;
        }
        return new ResultEntity(HttpStatus.OK.value());
    }

    @RequestMapping(value = "/update-status", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(@RequestParam("ids") String ids, @RequestParam("action") Integer action, HttpServletRequest request) {
        if(!CommonUtils.isEmpty(ids) && action != null) {
            try {
                List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
                userService.updateStatus(CommonUtils.strings2Longs(list), action);
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
        }
        return new ResultEntity(HttpStatus.OK.value());
    }

    @RequestMapping(value = "/repasswd", method = RequestMethod.POST)
    @ResponseBody
    public Object repasswd(@RequestParam("ids") String ids, @RequestParam("pwd") String pwd, HttpServletRequest request) {
        if(!CommonUtils.isEmpty(ids)) {
            try {
                List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
                userService.repasswd(CommonUtils.strings2Longs(list), EncryptUtils.encrypt(pwd));
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
        }
        return new ResultEntity(HttpStatus.OK.value(), getMessage("common.reasswdSuccess", request));
    }

}

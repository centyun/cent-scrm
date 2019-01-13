package com.centyun.user.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.core.util.CommonUtils;
import com.centyun.user.domain.Tenant;
import com.centyun.user.service.TenantService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/tenant")
public class TenantController extends BaseController {
    
    private Logger log = LoggerFactory.getLogger(TenantController.class);
    
    @Autowired
    private TenantService tenantService;

    // setSession和getSession不处理实际的业务, 仅做session共享的测试
    @RequestMapping(value = "/setsession", method = RequestMethod.POST)
    @ResponseBody
    public Object setSession(@RequestParam(required=false) Integer page, HttpSession session) {
        session.setAttribute("page", page);
        return session.getId();
    }
    
    @RequestMapping(value = "/getsession", method = RequestMethod.POST)
    @ResponseBody
    public Object getSession(HttpSession session) {
        Object page = session.getAttribute("page");
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("page", page);
        return map;
    }

    @RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.setViewName("tenant/tenant-index");
        return model;
    }

    @RequestMapping(value = "/tenants")
    @ResponseBody
    public Object getTenants(@ModelAttribute DataTableParam dataTableParam) {
        PageInfo<Tenant> tenants = tenantService.getTenants(dataTableParam);
        return new DataTableResult<Tenant>(tenants.getList(), tenants.getTotal(), dataTableParam.getDraw());
    }

    @RequestMapping(value = "/add.html")
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.setViewName("tenant/tenant-add");
        return model;
    }

    @RequestMapping(value = "/edit.html")
    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        Tenant tenant = tenantService.getTenantById(id);
        model.addObject("tenant", tenant);
        model.setViewName("tenant/tenant-edit");
        return model;
    }

    @RequestMapping(value = "/view.html")
    public ModelAndView view(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        Tenant tenant = tenantService.getTenantById(id);
        model.addObject("tenant", tenant);
        model.setViewName("tenant/tenant-view");
        return model;
    }

    @RequestMapping(value = "/save-tenant", method = RequestMethod.POST)
    @ResponseBody
    public Object saveTenant(@Validated Tenant tenant, HttpServletRequest request) {
        try {
            tenantService.saveTenant(tenant);
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

    @RequestMapping(value = "/update-status", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(@RequestParam("ids") String ids, @RequestParam("action") Integer action, HttpServletRequest request) {
        if(!CommonUtils.isEmpty(ids) && action != null) {
            try {
                List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
                tenantService.updateStatus(CommonUtils.strings2Longs(list), action);
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
}

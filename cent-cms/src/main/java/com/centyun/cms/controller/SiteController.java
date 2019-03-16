package com.centyun.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.cms.domain.Site;
import com.centyun.cms.domain.SiteAttribute;
import com.centyun.cms.service.SiteService;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.web.controller.WebBaseController;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/site-admin/site")
public class SiteController extends WebBaseController {
    
    private Logger log = LoggerFactory.getLogger(SiteController.class);
    
    @Autowired
    private SiteService siteService;

    @RequestMapping({"", "/", "/index.html"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/site/site-index");
        return model;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object getSites(@ModelAttribute DataTableParam dataTableParam, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if(user != null && !StringUtils.isEmpty(user.getTenantId())) {
            String tenantId = user.getTenantId();
            PageInfo<Site> tenants = siteService.getPageSites(dataTableParam, StringUtils.isEmpty(tenantId) ? null : tenantId);
            return new DataTableResult<Site>(tenants.getList(), tenants.getTotal(), dataTableParam.getDraw());
        }
        return null;
    }

    @RequestMapping(value = "/save-site", method = RequestMethod.POST)
    @ResponseBody
    public Object saveSite(Site site, HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
            if(user != null && !StringUtils.isEmpty(user.getTenantId())) {
                site.setTenantId(user.getTenantId());
                siteService.saveSite(site);
            }
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

    @RequestMapping(value = "/add.html")
    public ModelAndView addSite(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if(user != null && !StringUtils.isEmpty(user.getTenantId())) {
            model.addObject("products", getProductsAndModules(request));
            model.addObject("consoleUrl", consoleUrl);
            String tenantId = user.getTenantId();
            model.addObject("tenantId", tenantId);
            model.setViewName("admin/site/site-add");
        }
        return model;
    }

    @RequestMapping(value = "/edit.html")
    public ModelAndView edit(@RequestParam("id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if(user != null && !StringUtils.isEmpty(user.getTenantId())) {
            model.addObject("products", getProductsAndModules(request));
            model.addObject("consoleUrl", consoleUrl);
            String tenantId = user.getTenantId();
            Site site = siteService.getSite(tenantId, id);
            model.addObject("site", site);
            model.setViewName("admin/site/site-edit");
        }
        return model;
    }

    @RequestMapping(value = "/view.html")
    public ModelAndView view(@RequestParam("id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if(user != null && !StringUtils.isEmpty(user.getTenantId())) {
            model.addObject("products", getProductsAndModules(request));
            model.addObject("consoleUrl", consoleUrl);
            String tenantId = user.getTenantId();
            Site site = siteService.getSite(tenantId, id);
            model.addObject("site", site);
            model.setViewName("admin/site/site-view");
        }
        return model;
    }
    
    @RequestMapping("attribute")
    public ModelAndView attribute(@RequestParam(value="id", required=false) String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if(user != null && !StringUtils.isEmpty(user.getTenantId())) {
            String tenantId = user.getTenantId();
            model.addObject("sites", siteService.listAllSites(tenantId));
            model.addObject("products", getProductsAndModules(request));
            SiteAttribute attribute = siteService.getSiteAttribute(tenantId, id);
            if(attribute == null) {
                attribute = new SiteAttribute();
                attribute.setSiteId(id);
            }
            model.addObject("attribute", attribute);
            model.addObject("consoleUrl", consoleUrl);
            model.setViewName("admin/site/site-attribute");
        }
        return model;
    }
    
    @RequestMapping("attributeEdit")
    public ModelAndView attributeEdit(@RequestParam(value="id", required=false) String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if(user != null && !StringUtils.isEmpty(user.getTenantId())) {
            String tenantId = user.getTenantId();
            model.addObject("sites", siteService.listAllSites(tenantId));
            model.addObject("products", getProductsAndModules(request));
            SiteAttribute attribute = siteService.getSiteAttribute(tenantId, id);
            if(attribute == null) {
                attribute = new SiteAttribute();
                attribute.setSiteId(id);
            }
            model.addObject("attribute", attribute);
            model.addObject("consoleUrl", consoleUrl);
            model.setViewName("admin/site/site-attribute-edit");
        }
        return model;
    }
    
    @RequestMapping("parameter")
    public ModelAndView parameter(@RequestParam(value="id", required=false) String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if(user != null && !StringUtils.isEmpty(user.getTenantId())) {
            String tenantId = user.getTenantId();
            model.addObject("sites", siteService.listAllSites(tenantId));
        }
        model.addObject("products", getProductsAndModules(request));
        model.addObject("id", id);
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/site/site-parameter");
        return model;
    }

}

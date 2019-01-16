package com.centyun.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.cms.domain.Site;
import com.centyun.cms.service.SiteService;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.User;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/console-board/site")
public class SiteController extends BaseController {
    
    @Autowired
    private SiteService siteService;

    @RequestMapping({"", "/"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getAvailableProducts(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/site/site-index");
        return model;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object getSites(@ModelAttribute DataTableParam dataTableParam, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        if(user != null && user.getTenantId() > 0) {
            Long tenantId = user.getTenantId();
            PageInfo<Site> tenants = siteService.getPageSites(dataTableParam, tenantId == null || tenantId <= 0 ? null : tenantId);
            return new DataTableResult<Site>(tenants.getList(), tenants.getTotal(), dataTableParam.getDraw());
        }
        return null;
    }

}

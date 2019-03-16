package com.centyun.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.cms.service.SiteAttributeService;
import com.centyun.web.controller.WebBaseController;

@Controller
@RequestMapping(value = "/site-admin/parameter")
public class SiteConfigController extends WebBaseController {

    @Autowired
    private SiteAttributeService siteConfigService;
    
    @RequestMapping({"", "/"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/parameter/parameter-index");
        return model;
    }

}

package com.centyun.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.cms.service.SiteConfigService;

@Controller
@RequestMapping(value = "/console-board/config")
public class SiteConfigController extends BaseController {

    @Autowired
    private SiteConfigService siteConfigService;
    
    @RequestMapping({"", "/"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getAvailableProducts(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/config/config-index");
        return model;
    }

}

package com.centyun.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/console-board/nav")
public class NavigationController extends BaseController {

    @RequestMapping({"", "/"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getAvailableProducts(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/nav/nav-index");
        return model;
    }
}

package com.centyun.trackback.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.web.controller.WebBaseController;

public class TracebackController extends WebBaseController {

    @RequestMapping({ "", "/", "index.html" })
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("index");
        return model;
    }

}

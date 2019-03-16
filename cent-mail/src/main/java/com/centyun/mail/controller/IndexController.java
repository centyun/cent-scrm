package com.centyun.mail.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.web.controller.WebBaseController;

@Controller
public class IndexController extends WebBaseController {

    @RequestMapping(value = { "", "/", "/index.html" })
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("index");
        return model;
    }

}

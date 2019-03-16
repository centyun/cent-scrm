package com.centyun.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.web.controller.WebBaseController;

@Controller
@RequestMapping(value = "/site-admin/friendlink")
public class FriendLinkController extends WebBaseController {

    @RequestMapping({"", "/"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/friendlink/friendlink-index");
        return model;
    }
}

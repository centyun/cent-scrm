package com.centyun.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/console-board/article")
public class ArticleController extends BaseController {

    @RequestMapping({"", "/"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getAvailableProducts(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/article/article-index");
        return model;
    }

    @RequestMapping("/add-article")
    public ModelAndView addArticle(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getAvailableProducts(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/article/article-add");
        return model;
    }

    @RequestMapping("/edit-article")
    public ModelAndView editArticle(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getAvailableProducts(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/article/article-edit");
        return model;
    }

    @RequestMapping("/save-article")
    public ModelAndView saveArticle(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getAvailableProducts(request));
        model.addObject("consoleUrl", consoleUrl);
        model.setViewName("admin/article/article-index");
        return model;
    }
}

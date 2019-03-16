package com.centyun.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.user.domain.Continent;
import com.centyun.user.service.ContinentService;
import com.github.pagehelper.PageInfo;

@Controller
public class RegionController extends BaseController {
    
    @Autowired
    private ContinentService continentService;

    @RequestMapping("/location")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.setViewName("location/continent-index");
        return model;
    }

    @RequestMapping("/continent/list")
    @ResponseBody
    public Object listContinents(@ModelAttribute DataTableParam dataTableParam) {
        PageInfo<Continent> continents = continentService.listContinents(dataTableParam);
        return new DataTableResult<Continent>(continents.getList(), continents.getTotal(), dataTableParam.getDraw());
    }

}

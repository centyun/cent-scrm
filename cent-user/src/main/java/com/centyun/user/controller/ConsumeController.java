package com.centyun.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.user.domain.Consume;
import com.centyun.user.service.ConsumeService;
import com.centyun.user.service.TenantService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/consume")
public class ConsumeController extends BaseController {

    private Logger log = LoggerFactory.getLogger(ConsumeController.class);

    @Autowired
    private ConsumeService consumeService;

    @Autowired
    private TenantService tenantService;

    @RequestMapping({"", "/", "/index.html"})
    public ModelAndView index(@RequestParam(required = false) String tenantId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("tenantId", StringUtils.isEmpty(tenantId) ? null : tenantId);
        model.addObject("tenants", tenantService.getAllTenants());
        model.setViewName("consume/consume-index");
        return model;
    }

    @RequestMapping(value = "/consumes")
    @ResponseBody
    public Object getConsumes(@ModelAttribute DataTableParam dataTableParam,
            @RequestParam(required = false) String tenantId) {
        PageInfo<Consume> consumes = consumeService.getPageConsumes(dataTableParam,
                StringUtils.isEmpty(tenantId) ? null : tenantId);
        return new DataTableResult<Consume>(consumes.getList(), consumes.getTotal(), dataTableParam.getDraw());
    }

    @RequestMapping(value = "/view.html")
    public ModelAndView view(@RequestParam("id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        try {
            Consume consume = consumeService.getConsumeById(id);
            model.addObject("consume", consume);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        model.setViewName("consume/consume-view");
        return model;
    }
}

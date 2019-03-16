package com.centyun.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.user.domain.IpAddress;
import com.centyun.user.service.IpAddressService;
import com.centyun.user.service.ProvinceService;
import com.github.pagehelper.PageInfo;

@Controller
public class IpAddressController extends BaseController {

    @Autowired
    private IpAddressService ipService;

    @Autowired
    private ProvinceService provinceService;

    @RequestMapping({"/location/ip"})
    public ModelAndView ipIndex(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("provinces", provinceService.listAllProvinces("001")); // 只列出中国001的省
        model.setViewName("location/ip-index");
        return model;
    }

    @RequestMapping("/ip/list")
    @ResponseBody
    public Object listIps(@ModelAttribute DataTableParam dataTableParam, @RequestParam(required=false) String provinceId) throws Exception {
        PageInfo<IpAddress> ips = ipService.listPageIpAddresses(dataTableParam, provinceId);
        return new DataTableResult<IpAddress>(ips.getList(), ips.getTotal(), dataTableParam.getDraw());
    }
}

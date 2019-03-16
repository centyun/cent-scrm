package com.centyun.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.user.domain.Province;
import com.centyun.user.service.ContinentService;
import com.centyun.user.service.ProvinceService;
import com.github.pagehelper.PageInfo;

@Controller
public class ProvinceController extends BaseController {
    
    private Logger log = LoggerFactory.getLogger(ProvinceController.class);

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private ContinentService continentService;

    @RequestMapping({"/location/province"})
    public ModelAndView provinceIndex(@RequestParam(required=false) String countryRegionId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("countryRegionId", StringUtils.isEmpty(countryRegionId) ? AppConstant.EMPTY : countryRegionId);
        model.setViewName("location/province-index");
        return model;
    }

    @RequestMapping({"/province/list"})
    @ResponseBody
    public Object listProvinces(@ModelAttribute DataTableParam dataTableParam, @RequestParam(required=false) String countryRegionId) throws Exception {
        countryRegionId = "001"; // 目前只有中国的省份数据
        PageInfo<Province> provinces = provinceService.listPageProvinces(dataTableParam, countryRegionId);
        return new DataTableResult<Province>(provinces.getList(), provinces.getTotal(), dataTableParam.getDraw());
    }

    /**
     * 查看省详情
     * @param provinceId
     * @param request
     * @return
     */
    @RequestMapping({"/location/province/{provinceId}"})
    public ModelAndView province(@PathVariable String provinceId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        Province province = provinceService.getProvinceById(provinceId);
        model.addObject("province", province);
        model.setViewName("location/province-view");
        return model;
    }
    
    @RequestMapping({"/location/province/edit.html"})
    public ModelAndView provinceEdit(@RequestParam String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        Province province = provinceService.getProvinceById(id);
        model.addObject("province", province);
        model.addObject("continents", continentService.listAllContinents());
        model.setViewName("location/province-edit");
        return model;
    }
    
    @RequestMapping({"/location/province/add.html"})
    public ModelAndView provinceAdd(@RequestParam(required=false) String continentId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("continentId", StringUtils.isEmpty(continentId) ? AppConstant.EMPTY : continentId);
        model.addObject("continents", continentService.listAllContinents());
        model.setViewName("location/province-add");
        return model;
    }

    @RequestMapping(value = "/location/save-province", method = RequestMethod.POST)
    @ResponseBody
    public Object saveProvince(Province province, HttpServletRequest request) {
        try {
            province.setCountryRegionId("001"); // 目前只允许创建中国的省份
            provinceService.saveProvince(province);
        } catch (BadRequestException e) {
            log.error(e.getMessage(), e);
            ResultEntity result = new ResultEntity();
            result.setData(getMessage(e.getMessage(), request));
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ResultEntity result = new ResultEntity();
            result.setData(e.getMessage());
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            return result;
        }
        return new ResultEntity(HttpStatus.OK.value());
    }

}

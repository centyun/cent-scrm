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
import com.centyun.user.domain.City;
import com.centyun.user.service.CityService;
import com.centyun.user.service.ProvinceService;
import com.github.pagehelper.PageInfo;

@Controller
public class CityController extends BaseController {
    
    private Logger log = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @Autowired
    private ProvinceService provinceService;

    @RequestMapping({"/location/city"})
    public ModelAndView cityIndex(@RequestParam(required=false) String provinceId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("provinceId", provinceId);
        model.addObject("provinces", provinceService.listAllProvinces("001")); // 只列出中国001的省
        model.setViewName("location/city-index");
        return model;
    }

    @RequestMapping("/city/list")
    @ResponseBody
    public Object listCities(@ModelAttribute DataTableParam dataTableParam, @RequestParam(required=false) String provinceId) throws Exception {
        PageInfo<City> cities = cityService.listPageCitiesByProvinceId(dataTableParam, provinceId);
        return new DataTableResult<City>(cities.getList(), cities.getTotal(), dataTableParam.getDraw());
    }
    
    /**
     * 查看城市详情
     * @param cityId
     * @param request
     * @return
     */
    @RequestMapping({"/location/city/{cityId}"})
    public ModelAndView city(@PathVariable String cityId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        City city = cityService.getCityById(cityId);
        model.addObject("city", city);
        model.setViewName("location/city-view");
        return model;
    }
    
    @RequestMapping({"/location/city/edit.html"})
    public ModelAndView cityEdit(@RequestParam String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        City city = cityService.getCityById(id);
        model.addObject("city", city);
        model.addObject("provinces", provinceService.listAllProvinces("001")); // 只列出中国001的省
        model.setViewName("location/city-edit");
        return model;
    }
    
    @RequestMapping({"/location/city/add.html"})
    public ModelAndView cityAdd(@RequestParam(required=false) String provinceId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("provinceId", StringUtils.isEmpty(provinceId) ? AppConstant.EMPTY : provinceId);
        model.addObject("provinces", provinceService.listAllProvinces("001")); // 只列出中国001的省
        model.setViewName("location/city-add");
        return model;
    }

    @RequestMapping(value = "/location/save-city", method = RequestMethod.POST)
    @ResponseBody
    public Object saveCity(City city, HttpServletRequest request) {
        try {
            cityService.saveCity(city);
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

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
import com.centyun.user.domain.CountryRegion;
import com.centyun.user.service.ContinentService;
import com.centyun.user.service.CountryRegionService;
import com.github.pagehelper.PageInfo;

@Controller
public class CountryRegionController extends BaseController {
    
    private Logger log = LoggerFactory.getLogger(CountryRegionController.class);

    @Autowired
    private ContinentService continentService;

    @Autowired
    private CountryRegionService countryRegionService;

    @RequestMapping({"/location/country-region"})
    public ModelAndView countryRegionIndex(@RequestParam(required=false) String continentId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("continentId", StringUtils.isEmpty(continentId) ? AppConstant.EMPTY : continentId);
        model.addObject("continents", continentService.listAllContinents());
        model.setViewName("location/country-region-index");
        return model;
    }

    /**
     * 国家地区接口
     * @param dataTableParam
     * @param continentId
     * @return
     * @throws Exception
     */
    @RequestMapping({"/country-region/list"})
    @ResponseBody
    public Object countryRegions(@ModelAttribute DataTableParam dataTableParam, @RequestParam(required=false) String continentId) throws Exception {
        PageInfo<CountryRegion> countryRegions = countryRegionService.listPageCountryRegions(dataTableParam, continentId);
        return new DataTableResult<CountryRegion>(countryRegions.getList(), countryRegions.getTotal(), dataTableParam.getDraw());
    }
    
    /**
     * 查看国家地区详情
     * @param countryRegionId
     * @param request
     * @return
     */
    @RequestMapping({"/location/country-region/{countryRegionId}"})
    public ModelAndView countryRegion(@PathVariable String countryRegionId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        CountryRegion countryRegion = countryRegionService.getCountryRegionById(countryRegionId);
        model.addObject("countryRegion", countryRegion);
        model.setViewName("location/country-region-view");
        return model;
    }
    
    @RequestMapping({"/location/country-region/edit.html"})
    public ModelAndView countryRegionEdit(@RequestParam String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        CountryRegion countryRegion = countryRegionService.getCountryRegionById(id);
        model.addObject("countryRegion", countryRegion);
        model.addObject("continents", continentService.listAllContinents());
        model.setViewName("location/country-region-edit");
        return model;
    }
    
    @RequestMapping({"/location/country-region/add.html"})
    public ModelAndView countryRegionAdd(@RequestParam(required=false) String continentId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("continentId", StringUtils.isEmpty(continentId) ? AppConstant.EMPTY : continentId);
        model.addObject("continents", continentService.listAllContinents());
        model.setViewName("location/country-region-add");
        return model;
    }

    @RequestMapping(value = "/location/save-country-region", method = RequestMethod.POST)
    @ResponseBody
    public Object saveCountryRegion(CountryRegion countryRegion, HttpServletRequest request) {
        try {
            countryRegionService.saveCountryRegion(countryRegion);
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

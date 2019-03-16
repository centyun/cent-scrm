package com.centyun.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.base.domain.Dwz;
import com.centyun.base.service.DwzService;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.web.controller.WebBaseController;
import com.github.pagehelper.PageInfo;

@Controller
public class DwzController extends WebBaseController {
    
    private Logger log = LoggerFactory.getLogger(DwzController.class);
    
    @Autowired
    private DwzService dwzService;

    @RequestMapping({"", "/", "/dwz"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
        model.setViewName("dwz/dwz-index");
        return model;
    }

    @RequestMapping("/dwz/list")
    @ResponseBody
    public Object listDwzs(@ModelAttribute DataTableParam dataTableParam) {
        PageInfo<Dwz> continents = dwzService.listPageDwzs(dataTableParam);
        return new DataTableResult<Dwz>(continents.getList(), continents.getTotal(), dataTableParam.getDraw());
    }

    @RequestMapping({"/d/{dwzCode}"})
    public String dwzRedirect(@PathVariable String dwzCode, HttpServletRequest request) {
        Dwz dwz = dwzService.getDwzByCode(dwzCode);
        return "redirect:" + dwz.getLongUrl();
    }
    
    /**
     * 查看短网址详情
     * @param dwzId
     * @param request
     * @return
     */
    @RequestMapping({"/dwz/{dwzId}"})
    public ModelAndView dwzView(@PathVariable String dwzId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
        Dwz dwz = dwzService.getDwzById(dwzId);
        model.addObject("dwz", dwz);
        model.setViewName("dwz/dwz-view");
        return model;
    }
    
    @RequestMapping({"/dwz/add.html"})
    public ModelAndView cityAdd(@RequestParam(required=false) String provinceId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("products", getProductsAndModules(request));
        model.setViewName("dwz/dwz-add");
        return model;
    }

    @RequestMapping(value = "/dwz/save-dwz", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDwz(Dwz dwz, HttpServletRequest request) {
        Dwz savedDwz = null;
        try {
            User user = (User) request.getSession().getAttribute(AppConstant.LOGIN_USER);
            if (user == null) {
                throw new BadRequestException(AppConstant.AUTH_FAIL);
            }
            savedDwz = dwzService.getDwzByLongUrl(dwz, user);
            log.debug("code==" + savedDwz.getCode());
            log.debug("ShortUrl==" + savedDwz.getShortUrl());
            log.debug("qrcode==" + savedDwz.getQrcode());
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
        ResultEntity resultEntity = new ResultEntity(HttpStatus.OK.value());
        resultEntity.setData(savedDwz);
        return resultEntity;
    }

}

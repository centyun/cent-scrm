package com.centyun.user.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.core.util.CommonUtils;
import com.centyun.user.domain.Charge;
import com.centyun.user.service.ChargeService;
import com.centyun.user.service.ProductService;
import com.centyun.user.service.TenantService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/charge")
public class ChargeController extends BaseController {

    private Logger log = LoggerFactory.getLogger(ChargeController.class);

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/index.html")
    public ModelAndView index(@RequestParam(required = false) Long tenantId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("tenantId", tenantId == null || tenantId <= 0 ? null : tenantId);
        model.addObject("tenants", tenantService.getAllTenants());
        model.setViewName("charge/charge-index");
        return model;
    }

    @RequestMapping(value = "/charges")
    @ResponseBody
    public Object getCharges(@ModelAttribute DataTableParam dataTableParam,
            @RequestParam(required = false) Long tenantId) {
        PageInfo<Charge> charges = chargeService.getPageCharges(dataTableParam,
                tenantId == null || tenantId <= 0 ? null : tenantId);
        return new DataTableResult<Charge>(charges.getList(), charges.getTotal(), dataTableParam.getDraw());
    }

    @RequestMapping(value = "/add.html")
    public ModelAndView add(@RequestParam(required = false, value = "tenantId") Long tenantId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.addObject("tenants", tenantService.getAllTenants());
        if (tenantId != null && tenantId > 0) {
            model.addObject("tenantId", tenantId);
        }
        model.addObject("products", productService.getAvailableProducts());
        model.setViewName("charge/charge-add");
        return model;
    }

    @RequestMapping(value = "/view.html")
    public ModelAndView view(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        Charge charge = chargeService.getChargeById(id);
        model.addObject("charge", charge);
        model.setViewName("charge/charge-view");
        return model;
    }

    @RequestMapping(value = "/save-charge", method = RequestMethod.POST)
    @ResponseBody
    public Object saveCharge(Charge charge, HttpServletRequest request) {
        try {
            chargeService.saveCharge(charge);
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

    @RequestMapping(value = "/update-status", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(@RequestParam("ids") String ids, @RequestParam("action") Integer action, HttpServletRequest request) {
        if (!CommonUtils.isEmpty(ids) && action != null) {
            try {
                List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
                // 2取消充值
                chargeService.updateStatus(CommonUtils.strings2Longs(list), action);
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
        }
        return new ResultEntity(HttpStatus.OK.value());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // 转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
}

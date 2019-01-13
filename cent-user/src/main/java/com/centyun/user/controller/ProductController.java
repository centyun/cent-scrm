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
import com.centyun.core.domain.Product;
import com.centyun.core.domain.ProductVO;
import com.centyun.core.domain.ResultEntity;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.DataTableResult;
import com.centyun.core.util.CommonUtils;
import com.centyun.user.service.ProductService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {

    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.setViewName("product/product-index");
        return model;
    }

    @RequestMapping(value = "/products")
    @ResponseBody
    public Object getProducts(@ModelAttribute DataTableParam dataTableParam) {
        PageInfo<Product> products = productService.getProducts(dataTableParam);
        return new DataTableResult<Product>(products.getList(), products.getTotal(), dataTableParam.getDraw());
    }

    @RequestMapping(value = "/add.html")
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        model.setViewName("product/product-add");
        return model;
    }

    @RequestMapping(value = "/edit.html")
    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        Product product = productService.getProductById(id);
        model.addObject("product", product);
        model.setViewName("product/product-edit");
        return model;
    }

    @RequestMapping(value = "/view.html")
    public ModelAndView view(@RequestParam("id") Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("modules", getModules(request));
        Product product = productService.getProductById(id);
        model.addObject("product", product);
        model.setViewName("product/product-view");
        return model;
    }

    @RequestMapping(value = "/save-product", method = RequestMethod.POST)
    @ResponseBody
    public Object saveProduct(Product product, HttpServletRequest request) {
        try {
            productService.saveProduct(product);
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
                productService.updateStatus(CommonUtils.strings2Longs(list), action);
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

    @RequestMapping(value = "/getAvailableProducts")
    @ResponseBody
    public Object getAvailableProducts() {
        List<ProductVO> products = productService.getAvailableProducts();
        return products;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // 转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
}

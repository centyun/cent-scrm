package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.core.domain.Product;
import com.centyun.core.domain.ProductVO;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.user.constant.UserConstant;
import com.centyun.user.domain.Manager;
import com.centyun.user.mapper.ProductMapper;
import com.centyun.user.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> getProducts(DataTableParam dataTableParam) {
        PageHelper.startPage(dataTableParam.getStart(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Product> products = productMapper.getPageProducts(StringUtils.isEmpty(searchValue) ? null: searchValue, 
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.getProductById(id);
    }

    @Override
    public void saveProduct(Product product) {
        // 获取当前用户
        Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(manager == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        if(checkProduct(product)) {
            throw new BadRequestException(UserConstant.TENANT_EXISTED);
        }
        Long id = product.getId();
        if(id == null || id <= 0) {
            SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(UserConstant.DATACENTER_ID, UserConstant.MACHINE_ID);
            product.setId(snowFlake.nextId());
            product.setCreator(manager.getId());
            productMapper.addProduct(product);
        } else {
            product.setEditor(manager.getId());
            productMapper.updateProduct(product);
        }
    }

    private boolean checkProduct(Product product) {
        int count = productMapper.getProductCountByName(product);
        return count > 0;
    }

    @Override
    public void updateStatus(List<Long> ids, Integer action) {
        Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(manager == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        // action转换状态: 注销0转换为下线停用, 启用1转换为正常
        Integer status = action == 0 ? UserConstant.PRODUCT_STATUS_OFFLINE : UserConstant.PRODUCT_STATUS_OK;
        productMapper.updateStatus(ids, status, manager.getId());
    }

    @Override
    public List<ProductVO> getAvailableProducts() {
        return productMapper.getAvailableProducts();
    }

}

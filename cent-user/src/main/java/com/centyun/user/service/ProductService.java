package com.centyun.user.service;

import java.util.List;

import com.centyun.core.domain.Product;
import com.centyun.core.domain.ProductVO;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface ProductService {

    PageInfo<Product> getProducts(DataTableParam dataTableParam);

    Product getProductById(Long id);

    void saveProduct(Product product);

    void updateStatus(List<Long> ids, Integer action);

    List<ProductVO> getAvailableProducts();
}

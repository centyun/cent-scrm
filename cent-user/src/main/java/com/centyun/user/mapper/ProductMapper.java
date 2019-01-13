package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.domain.Product;
import com.centyun.core.domain.ProductVO;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface ProductMapper {

    List<Product> getPageProducts(@Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);

    Product getProductById(@Param("id") Long id);

    void addProduct(Product product);

    void updateProduct(Product product);

    void updateStatus(@Param("ids") List<Long> ids, @Param("status") int status, @Param("editor") Long editor);

    List<ProductVO> getAvailableProducts();

    int getProductCountByName(Product product);

}

package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.table.KeyValuePair;
import com.centyun.user.domain.Province;

@Mapper
public interface ProvinceMapper {
    
    List<Province> listProvinces(@Param("countryRegionId") String countryRegionId);

    List<Province> listPageProvinces(@Param("countryRegionId") String countryRegionId, @Param("searchValue") String searchValue
            , @Param("orders") List<KeyValuePair> orders);

    Province getProvinceById(@Param("provinceId") String provinceId);

    void addProvince(Province province);

    void updateProvince(Province province);

    int getProvinceByName(Province province);

    Province getProvinceByNameAlias(@Param("name") String name);

}

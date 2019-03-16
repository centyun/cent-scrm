package com.centyun.user.service;

import java.util.List;

import com.centyun.core.table.DataTableParam;
import com.centyun.user.domain.Province;
import com.github.pagehelper.PageInfo;

public interface ProvinceService {
    
    PageInfo<Province> listPageProvinces(DataTableParam dataTableParam, String countryRegionId);
    
    List<Province> listAllProvinces(String countryRegionId);

    Province getProvinceById(String provinceId);

    void saveProvince(Province province);
    
    Province getProvinceByName(String name);

}

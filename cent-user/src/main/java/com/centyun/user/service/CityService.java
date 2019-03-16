package com.centyun.user.service;

import java.util.List;

import com.centyun.core.table.DataTableParam;
import com.centyun.user.domain.City;
import com.github.pagehelper.PageInfo;

public interface CityService {

    PageInfo<City> listPageCitiesByProvinceId(DataTableParam dataTableParam, String provinceId);
    
    List<City> listCitiesByProvinceId(String provinceId);

    City getCityById(String cityId);
    
    void saveCity(City city);

    City getCityByName(String name);
    
}

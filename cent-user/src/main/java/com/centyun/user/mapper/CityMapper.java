package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.table.KeyValuePair;
import com.centyun.user.domain.City;

@Mapper
public interface CityMapper {
    
    List<City> listCitiesByProvinceId(@Param("provinceId") String provinceId);

    List<City> listPageCitiesByProvinceId(@Param("provinceId") String provinceId, @Param("searchValue") String searchValue
            , @Param("orders") List<KeyValuePair> orders);

    City getCityById(@Param("cityId") String cityId);

    void addCity(City city);

    void updateCity(City city);
    
    int getCityByName(City city);

    City getCityByNameAlias(@Param("name") String name);

}

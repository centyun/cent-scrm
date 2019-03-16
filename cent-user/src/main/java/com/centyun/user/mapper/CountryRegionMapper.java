package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.table.KeyValuePair;
import com.centyun.user.domain.CountryRegion;

@Mapper
public interface CountryRegionMapper {

    List<CountryRegion> listCountryRegions(@Param("continentId") String continentId);

    List<CountryRegion> listPageCountryRegions(@Param("continentId") String continentId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);

    CountryRegion getCountryRegionById(@Param("countryRegionId") String countryRegionId);

    void addCountryRegion(CountryRegion countryRegion);

    void updateCountryRegion(CountryRegion countryRegion);

    int getCountryRegionByName(CountryRegion countryRegion);

}

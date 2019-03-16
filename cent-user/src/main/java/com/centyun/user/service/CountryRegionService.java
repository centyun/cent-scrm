package com.centyun.user.service;

import java.util.List;

import com.centyun.core.table.DataTableParam;
import com.centyun.user.domain.CountryRegion;
import com.github.pagehelper.PageInfo;

public interface CountryRegionService {
    
    PageInfo<CountryRegion> listPageCountryRegions(DataTableParam dataTableParam, String continentId);
    
    List<CountryRegion> listAllCountryRegions(String continentId);

    CountryRegion getCountryRegionById(String countryRegionId);

    void saveCountryRegion(CountryRegion countryRegion);

}

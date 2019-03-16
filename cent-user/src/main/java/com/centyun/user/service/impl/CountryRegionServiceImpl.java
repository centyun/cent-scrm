package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.core.domain.Administrator;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.user.constant.UserConstant;
import com.centyun.user.domain.CountryRegion;
import com.centyun.user.mapper.CountryRegionMapper;
import com.centyun.user.service.CountryRegionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CountryRegionServiceImpl implements CountryRegionService {
    
    @Autowired
    private CountryRegionMapper countryRegionMapper;

    @Override
    public PageInfo<CountryRegion> listPageCountryRegions(DataTableParam dataTableParam, String continentId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<CountryRegion> countryRegions = countryRegionMapper.listPageCountryRegions(continentId, 
                StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<CountryRegion> pageInfo = new PageInfo<>(countryRegions);
        return pageInfo;
    }

    @Override
    public List<CountryRegion> listAllCountryRegions(String continentId) {
        return countryRegionMapper.listCountryRegions(continentId);
    }

    @Override
    public CountryRegion getCountryRegionById(String countryRegionId) {
        return countryRegionMapper.getCountryRegionById(countryRegionId);
    }

    @Override
    public void saveCountryRegion(CountryRegion countryRegion) {
        Administrator administrator = (Administrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (administrator == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        if (checkCountryRegion(countryRegion)) {
            throw new BadRequestException(UserConstant.COUNTRY_REGION_EXISTED);
        }
        String id = countryRegion.getId();
        if (StringUtils.isEmpty(id)) {
            SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(UserConstant.DATACENTER_ID, UserConstant.MACHINE_ID);
            countryRegion.setId(snowFlake.nextId());
            countryRegion.setCreator(administrator.getId());
            countryRegionMapper.addCountryRegion(countryRegion);
        } else {
            countryRegion.setEditor(administrator.getId());
            countryRegionMapper.updateCountryRegion(countryRegion);
        }
    }

    private boolean checkCountryRegion(CountryRegion countryRegion) {
        int count = countryRegionMapper.getCountryRegionByName(countryRegion);
        return count > 0;
    }

}

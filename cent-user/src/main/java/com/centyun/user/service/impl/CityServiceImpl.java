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
import com.centyun.user.domain.City;
import com.centyun.user.mapper.CityMapper;
import com.centyun.user.service.CityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CityServiceImpl implements CityService {
    
    @Autowired
    private CityMapper cityMapper;

    @Override
    public PageInfo<City> listPageCitiesByProvinceId(DataTableParam dataTableParam, String provinceId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<City> users = cityMapper.listPageCitiesByProvinceId(provinceId, StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<City> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public List<City> listCitiesByProvinceId(String provinceId) {
        return cityMapper.listCitiesByProvinceId(provinceId);
    }

    @Override
    public City getCityById(String cityId) {
        return cityMapper.getCityById(cityId);
    }

    @Override
    public void saveCity(City city) {
        Administrator administrator = (Administrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (administrator == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        if (checkCity(city)) {
            throw new BadRequestException(UserConstant.CITY_EXISTED);
        }
        String id = city.getId();
        if (StringUtils.isEmpty(id)) {
            SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(UserConstant.DATACENTER_ID, UserConstant.MACHINE_ID);
            city.setId(snowFlake.nextId());
            city.setCreator(administrator.getId());
            cityMapper.addCity(city);
        } else {
            city.setEditor(administrator.getId());
            cityMapper.updateCity(city);
        }
    }

    private boolean checkCity(City city) {
        int count = cityMapper.getCityByName(city);
        return count > 0;
    }

    @Override
    public City getCityByName(String name) {
        return cityMapper.getCityByNameAlias(name);
    }

}

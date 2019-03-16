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
import com.centyun.user.domain.Province;
import com.centyun.user.mapper.ProvinceMapper;
import com.centyun.user.service.ProvinceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;
    
    @Override
    public PageInfo<Province> listPageProvinces(DataTableParam dataTableParam, String countryRegionId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Province> provinces = provinceMapper.listPageProvinces(countryRegionId, StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Province> pageInfo = new PageInfo<>(provinces);
        return pageInfo;
    }
    
    @Override
    public List<Province> listAllProvinces(String countryRegionId) {
        return provinceMapper.listProvinces(countryRegionId);
    }

    @Override
    public Province getProvinceById(String provinceId) {
        return provinceMapper.getProvinceById(provinceId);
    }

    @Override
    public void saveProvince(Province province) {
        Administrator administrator = (Administrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (administrator == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        if (checkProvince(province)) {
            throw new BadRequestException(UserConstant.PROVINCE_EXISTED);
        }
        String id = province.getId();
        if (StringUtils.isEmpty(id)) {
            SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(UserConstant.DATACENTER_ID, UserConstant.MACHINE_ID);
            province.setId(snowFlake.nextId());
            province.setCreator(administrator.getId());
            provinceMapper.addProvince(province);
        } else {
            province.setEditor(administrator.getId());
            provinceMapper.updateProvince(province);
        }
    }

    private boolean checkProvince(Province province) {
        int count = provinceMapper.getProvinceByName(province);
        return count > 0;
    }

    @Override
    public Province getProvinceByName(String name) {
        return provinceMapper.getProvinceByNameAlias(name);
    }

}

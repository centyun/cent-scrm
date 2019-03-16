package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.core.table.DataTableParam;
import com.centyun.user.domain.Continent;
import com.centyun.user.mapper.ContinentMapper;
import com.centyun.user.service.ContinentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContinentServiceImpl implements ContinentService {
    
    @Autowired
    private ContinentMapper continentMapper;

    @Override
    public PageInfo<Continent> listContinents(DataTableParam dataTableParam) {
        PageHelper.startPage(dataTableParam.getPageNum(), Integer.MAX_VALUE);
        List<Continent> continents = continentMapper.listContinents();
        PageInfo<Continent> pageInfo = new PageInfo<>(continents);
        return pageInfo;
    }

    @Override
    public List<Continent> listAllContinents() {
        List<Continent> continents = continentMapper.listContinents();
        return continents;
    }

}

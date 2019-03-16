package com.centyun.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.cms.domain.Navigation;
import com.centyun.cms.mapper.NavigationMapper;
import com.centyun.cms.service.NavigationService;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

@Service
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    @Override
    public void saveNavigation(Navigation navigation) {
        // TODO Auto-generated method stub

    }

    @Override
    public Navigation getNavigation(String tenantId, String siteId, String navId) {
        return navigationMapper.getNavigation(tenantId, siteId, navId);
    }

    @Override
    public PageInfo<Navigation> getPageNavigations(DataTableParam dataTableParam, String tenantId) {
        // TODO Auto-generated method stub
        return null;
    }

}

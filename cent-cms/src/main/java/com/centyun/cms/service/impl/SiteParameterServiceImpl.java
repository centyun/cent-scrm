package com.centyun.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.cms.domain.SiteParameter;
import com.centyun.cms.mapper.SiteParameterMapper;
import com.centyun.cms.service.SiteParameterService;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SiteParameterServiceImpl implements SiteParameterService {

    @Autowired
    private SiteParameterMapper siteParameterMapper;

    @Override
    public void saveSiteParameter(SiteParameter siteParameter) {
        if(siteParameter != null && siteParameter.getTenantId() != null) {
            SiteParameter config = siteParameterMapper.getSiteParameter(siteParameter.getSiteId(), siteParameter.getTenantId(), siteParameter.getParameter());
            if (config == null) {
                siteParameterMapper.addSiteParameter(siteParameter);
            } else {
                siteParameterMapper.updateSiteParameter(siteParameter);
            }
        }
    }

    @Override
    public SiteParameter getSiteParameter(Long siteId, Long tenantId, String parameter) {
        return siteParameterMapper.getSiteParameter(siteId, tenantId, parameter);
    }

    @Override
    public PageInfo<SiteParameter> getPageSiteParameters(DataTableParam dataTableParam, Long tenantId) {
        PageHelper.startPage(dataTableParam.getStart(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<SiteParameter> siteParameters = siteParameterMapper.getPageSiteParameters(tenantId, StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<SiteParameter> pageInfo = new PageInfo<>(siteParameters);
        return pageInfo;
    }

}

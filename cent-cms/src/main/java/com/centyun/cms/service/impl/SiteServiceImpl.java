package com.centyun.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.cms.constant.SieConstant;
import com.centyun.cms.domain.Site;
import com.centyun.cms.domain.SiteAttribute;
import com.centyun.cms.mapper.SiteMapper;
import com.centyun.cms.service.SiteService;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SiteServiceImpl implements SiteService {
    
    @Autowired
    private SiteMapper siteMapper;

    @Override
    public void saveSite(Site site) {
        if(site != null && site.getTenantId() != null) {
            String id = site.getId();
            if (StringUtils.isEmpty(id)) {
                SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(SieConstant.DATACENTER_ID, SieConstant.MACHINE_ID);
                site.setId(snowFlake.nextId());
                siteMapper.addSite(site);
            } else {
                siteMapper.updateSite(site);
            }
        }
    }

    @Override
    public Site getSite(String tenantId, String id) {
        return siteMapper.getSite(tenantId, id);
    }

    @Override
    public PageInfo<Site> getPageSites(DataTableParam dataTableParam, String tenantId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Site> sites = siteMapper.getPageSites(tenantId, StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Site> pageInfo = new PageInfo<>(sites);
        return pageInfo;
    }

    @Override
    public List<Site> listAllSites(String tenantId) {
        List<Site> sites = siteMapper.listAllSites(tenantId);
        return sites;
    }

    @Override
    public SiteAttribute getSiteAttribute(String tenantId, String id) {
        return siteMapper.getSiteAttribute(tenantId, id);
    }

}

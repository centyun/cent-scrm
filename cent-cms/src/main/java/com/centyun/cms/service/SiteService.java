package com.centyun.cms.service;

import java.util.List;

import com.centyun.cms.domain.Site;
import com.centyun.cms.domain.SiteAttribute;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface SiteService {
    
    void saveSite(Site site);
    
    Site getSite(String tenantId, String id);

    PageInfo<Site> getPageSites(DataTableParam dataTableParam, String tenantId);

    List<Site> listAllSites(String tenantId);

    SiteAttribute getSiteAttribute(String tenantId, String id);
    
}

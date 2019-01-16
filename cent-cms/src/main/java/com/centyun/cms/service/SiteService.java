package com.centyun.cms.service;

import com.centyun.cms.domain.Site;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface SiteService {
    
    void saveSite(Site site);
    
    Site getSite(Long tenantId, Long id);

    PageInfo<Site> getPageSites(DataTableParam dataTableParam, Long tenantId);
    
}

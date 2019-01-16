package com.centyun.cms.service;

import com.centyun.cms.domain.SiteParameter;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface SiteParameterService {
    
    void saveSiteParameter(SiteParameter siteParameter);
    
    SiteParameter getSiteParameter(Long siteId, Long tenantId, String parameter);

    PageInfo<SiteParameter> getPageSiteParameters(DataTableParam dataTableParam, Long tenantId);

}

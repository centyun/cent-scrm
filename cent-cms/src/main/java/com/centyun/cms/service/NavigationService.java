package com.centyun.cms.service;

import com.centyun.cms.domain.Navigation;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface NavigationService {
    
    void saveNavigation(Navigation navigation);
    
    Navigation getNavigation(String tenantId, String siteId, String navId);

    PageInfo<Navigation> getPageNavigations(DataTableParam dataTableParam, String tenantId);
}

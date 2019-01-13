package com.centyun.user.service;

import java.util.List;

import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.user.domain.Tenant;
import com.github.pagehelper.PageInfo;

public interface TenantService {

    PageInfo<Tenant> getTenants(DataTableParam dataTableParam);

    List<KeyValuePair> getAllTenants();

    void saveTenant(Tenant tenant);

    Tenant getTenantById(Long id);

    void updateStatus(List<Long> ids, Integer action);

}

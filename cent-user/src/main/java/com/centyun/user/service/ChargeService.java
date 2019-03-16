package com.centyun.user.service;

import java.util.List;

import com.centyun.core.table.DataTableParam;
import com.centyun.user.domain.Charge;
import com.github.pagehelper.PageInfo;

public interface ChargeService {

    PageInfo<Charge> getPageCharges(DataTableParam dataTableParam, String tenantId);

    Charge getChargeById(String id);

    void saveCharge(Charge charge) throws Exception;

    void updateStatus(List<String> ids, Integer action) throws Exception;
}

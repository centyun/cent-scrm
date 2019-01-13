package com.centyun.user.service;

import java.util.List;

import com.centyun.core.table.DataTableParam;
import com.centyun.user.domain.Charge;
import com.github.pagehelper.PageInfo;

public interface ChargeService {

    PageInfo<Charge> getPageCharges(DataTableParam dataTableParam, Long tenantId);

    Charge getChargeById(Long id);

    void saveCharge(Charge charge) throws Exception;

    void updateStatus(List<Long> ids, Integer action) throws Exception;
}

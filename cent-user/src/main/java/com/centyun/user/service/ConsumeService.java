package com.centyun.user.service;

import com.centyun.core.table.DataTableParam;
import com.centyun.user.domain.Consume;
import com.github.pagehelper.PageInfo;

public interface ConsumeService {

    PageInfo<Consume> getPageConsumes(DataTableParam dataTableParam, String tenantId);

    Consume getConsumeById(String id);

    void saveConsume(Consume consume);
}

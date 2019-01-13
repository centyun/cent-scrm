package com.centyun.core.service;

import com.centyun.core.domain.Audit;

/**
 * 审计接口, 提出为通用的
 * @author yinww
 *
 */

public interface AuditService {
    
//    PageInfo<Audit> getAudits(DataTableParam dataTableParam);
    
    void saveAudit(Audit audit);

}

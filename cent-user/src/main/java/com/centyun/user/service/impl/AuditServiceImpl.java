package com.centyun.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.centyun.core.domain.Audit;
import com.centyun.core.service.AuditService;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.user.constant.UserConstant;
import com.centyun.user.mapper.AuditMapper;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditMapper auditMapper;
/*
    @Override
    public PageInfo<Audit> getAudits(DataTableParam dataTableParam) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Audit> consumes = auditMapper.getAudits(StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Audit> pageInfo = new PageInfo<>(consumes);
        return pageInfo;
    }
*/
    @Override
    public void saveAudit(Audit audit) {
        String id = audit.getId();
        if (StringUtils.isEmpty(id)) {
            SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(UserConstant.DATACENTER_ID, UserConstant.MACHINE_ID);
            audit.setId(snowFlake.nextId());
            auditMapper.addAudit(audit);
        }
    }

}

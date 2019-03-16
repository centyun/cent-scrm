package com.centyun.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.centyun.base.constant.BaseConstant;
import com.centyun.base.domain.WxAuth;
import com.centyun.base.mapper.WxAuthMapper;
import com.centyun.base.service.WxService;
import com.centyun.core.domain.User;
import com.centyun.core.util.SnowFlakeIdWorker;

@Service
public class WxServiceImpl implements WxService {
    
    @Autowired
    private WxAuthMapper wxAuthMapper;

    @Override
    public WxAuth getWxAuthById(String id) {
        return wxAuthMapper.getWxAuthById(id);
    }

    @Override
    public WxAuth getWxAuthByTenantId(String tenantId) {
        return wxAuthMapper.getWxAuthByTenantId(tenantId);
    }

    @Override
    public String saveWxAuth(WxAuth wxAuth, User user) {
        if(StringUtils.isEmpty(wxAuth.getId())) {
            SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(BaseConstant.DATACENTER_ID, BaseConstant.MACHINE_ID);
            String id = snowFlake.nextId();
            wxAuth.setId(id);
            wxAuth.setTenantId(user.getTenantId());
            wxAuth.setTenantName(user.getTenantName());
            wxAuth.setCreator(user.getId());
            wxAuth.setCreatorName(user.getDisplayName());
            wxAuthMapper.addWxAuth(wxAuth);
            return id;
        } else {
            wxAuth.setEditor(user.getId());
            wxAuth.setEditorName(user.getDisplayName());
            wxAuthMapper.updateWxAuth(wxAuth);
            return null;
        }
    }

}

package com.centyun.base.service;

import com.centyun.base.domain.WxAuth;
import com.centyun.core.domain.User;

public interface WxService {
    
    WxAuth getWxAuthById(String id);
    
    WxAuth getWxAuthByTenantId(String tenantId);
    
    String saveWxAuth(WxAuth city, User user);

}

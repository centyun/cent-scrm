package com.centyun.cms.service;

import com.centyun.cms.domain.SiteAttribute;

public interface SiteAttributeService {
    
    void saveSiteConfig(SiteAttribute siteConfig);
    
    SiteAttribute getSiteConfig(String siteId, String tenantId);

}

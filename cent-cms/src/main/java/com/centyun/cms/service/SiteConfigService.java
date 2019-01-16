package com.centyun.cms.service;

import com.centyun.cms.domain.SiteConfig;

public interface SiteConfigService {
    
    void saveSiteConfig(SiteConfig siteConfig);
    
    SiteConfig getSiteConfig(Long siteId, Long tenantId);

}

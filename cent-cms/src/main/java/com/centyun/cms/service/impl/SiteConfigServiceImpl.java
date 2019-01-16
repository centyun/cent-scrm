package com.centyun.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.cms.domain.SiteConfig;
import com.centyun.cms.mapper.SiteConfigMapper;
import com.centyun.cms.service.SiteConfigService;

@Service
public class SiteConfigServiceImpl implements SiteConfigService {
    
    @Autowired
    private SiteConfigMapper siteConfigMapper;

    @Override
    public void saveSiteConfig(SiteConfig siteConfig) {
        if(siteConfig != null && siteConfig.getTenantId() != null) {
            SiteConfig config = siteConfigMapper.getSiteConfig(siteConfig.getSiteId(), siteConfig.getTenantId());
            if (config == null) {
                siteConfigMapper.addSiteConfig(siteConfig);
            } else {
                siteConfigMapper.updateSiteConfig(siteConfig);
            }
        }
    }

    @Override
    public SiteConfig getSiteConfig(Long siteId, Long tenantId) {
        return siteConfigMapper.getSiteConfig(siteId, tenantId);
    }

}

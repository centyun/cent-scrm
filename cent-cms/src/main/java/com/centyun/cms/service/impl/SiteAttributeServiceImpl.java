package com.centyun.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.centyun.cms.domain.SiteAttribute;
import com.centyun.cms.mapper.SiteAttributeMapper;
import com.centyun.cms.service.SiteAttributeService;

@Service
public class SiteAttributeServiceImpl implements SiteAttributeService {
    
    @Autowired
    private SiteAttributeMapper siteAttributeMapper;

    @Override
    public void saveSiteConfig(SiteAttribute siteAttribute) {
        if(siteAttribute != null && !StringUtils.isEmpty(siteAttribute.getTenantId())) {
            SiteAttribute attribute = siteAttributeMapper.getSiteConfig(siteAttribute.getSiteId(), siteAttribute.getTenantId());
            if (attribute == null) {
                siteAttributeMapper.addSiteConfig(siteAttribute);
            } else {
                siteAttributeMapper.updateSiteConfig(siteAttribute);
            }
        }
    }

    @Override
    public SiteAttribute getSiteConfig(String siteId, String tenantId) {
        return siteAttributeMapper.getSiteConfig(siteId, tenantId);
    }

}

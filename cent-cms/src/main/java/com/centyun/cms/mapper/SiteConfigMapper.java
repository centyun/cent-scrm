package com.centyun.cms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.cms.domain.SiteConfig;

@Mapper
public interface SiteConfigMapper {
    
    void addSiteConfig(SiteConfig siteConfig);
    
    void updateSiteConfig(SiteConfig siteConfig);
    
    SiteConfig getSiteConfig(@Param("siteId") Long siteId, @Param("tenantId") Long tenantId);

}

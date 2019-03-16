package com.centyun.cms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.cms.domain.SiteAttribute;

@Mapper
public interface SiteAttributeMapper {
    
    void addSiteConfig(SiteAttribute siteConfig);
    
    void updateSiteConfig(SiteAttribute siteConfig);
    
    SiteAttribute getSiteConfig(@Param("siteId") String siteId, @Param("tenantId") String tenantId);

}

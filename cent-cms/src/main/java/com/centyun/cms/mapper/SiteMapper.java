package com.centyun.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.cms.domain.Site;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface SiteMapper {
    
    void addSite(Site site);
    
    void updateSite(Site site);
    
    Site getSite(@Param("tenantId") Long tenantId, @Param("id") Long id);

    List<Site> getPageSites(@Param("tenantId") Long tenantId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);

}

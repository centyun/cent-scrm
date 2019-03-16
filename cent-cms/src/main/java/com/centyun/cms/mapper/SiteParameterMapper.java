package com.centyun.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.cms.domain.SiteParameter;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface SiteParameterMapper {
    
    void addSiteParameter(SiteParameter siteParameter);
    
    void updateSiteParameter(SiteParameter siteParameter);
    
    SiteParameter getSiteParameter(@Param("siteId") String siteId, @Param("tenantId") String tenantId, @Param("name") String name);

    List<SiteParameter> getPageSiteParameters(@Param("tenantId") String tenantId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);
}

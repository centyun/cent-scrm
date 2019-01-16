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
    
    SiteParameter getSiteParameter(@Param("siteId") Long siteId, @Param("tenantId") Long tenantId, @Param("parameter") String parameter);

    List<SiteParameter> getPageSiteParameters(@Param("tenantId") Long tenantId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);
}

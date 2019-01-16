package com.centyun.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.cms.domain.Navigation;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface NavigationMapper {
    
    void addNavigation(Navigation navigation);
    
    void updateNavigation(Navigation navigation);
    
    Navigation getNavigation(@Param("tenantId") Long tenantId, @Param("siteId") Long siteId, @Param("navId") Long navId);

    List<Navigation> getPageNavigations(@Param("tenantId") Long tenantId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);

}

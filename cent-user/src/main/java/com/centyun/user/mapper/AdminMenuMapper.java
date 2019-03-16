package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.user.domain.AdminMenu;

@Mapper
public interface AdminMenuMapper {
    
    List<AdminMenu> getAdminMenus(@Param("userId") String userId, @Param("tenantId") String tenantId);

}

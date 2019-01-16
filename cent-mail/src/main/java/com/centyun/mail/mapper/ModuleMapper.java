package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.domain.Module;

@Mapper
public interface ModuleMapper {
    
    List<Module> getModules(@Param("userId") Long userId, @Param("tenantId") Long tenantId);

}

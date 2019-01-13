package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.domain.Module;
import com.centyun.user.domain.Manager;

@Mapper
public interface ManagerMapper {

    Manager getManager(@Param("loginName") String username);

    List<Module> getModules();

    void updateLanguage(Manager manager);

}

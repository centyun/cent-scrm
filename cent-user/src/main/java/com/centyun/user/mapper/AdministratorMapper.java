package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.domain.Administrator;
import com.centyun.user.domain.AdminMenu;

@Mapper
public interface AdministratorMapper {

    Administrator getAdministrator(@Param("loginName") String username);

    List<AdminMenu> getAdminMenus();

    void updateLanguage(Administrator administrator);

}

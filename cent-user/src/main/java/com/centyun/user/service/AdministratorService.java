package com.centyun.user.service;

import java.util.List;

import com.centyun.core.domain.Administrator;
import com.centyun.user.domain.AdminMenu;

public interface AdministratorService {

    List<AdminMenu> getAdminMenus();

    void updateLanguage(Administrator administrator);

}

package com.centyun.user.service;

import java.util.List;

import com.centyun.core.domain.Module;
import com.centyun.user.domain.Manager;

public interface ManagerService {

    List<Module> getModules();

    void updateLanguage(Manager manager);

}

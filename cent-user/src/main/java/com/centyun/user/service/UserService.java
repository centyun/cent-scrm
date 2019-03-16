package com.centyun.user.service;

import java.util.List;

import com.centyun.core.domain.User;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface UserService {

    User getUserById(String userId);

    void saveUser(User user);

    PageInfo<User> getPageUsers(DataTableParam dataTableParam, String tenantId);

    void updateStatus(List<String> ids, Integer action);

    void repasswd(List<String> ids, String passwd);

    void updateLanguage(String id, String language);

    User getUserByToken(String token);

}

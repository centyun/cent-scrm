package com.centyun.user.service;

import java.util.List;

import com.centyun.core.domain.User;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface UserService {

    User getUserById(Long userId);

    void saveUser(User user);

    PageInfo<User> getPageUsers(DataTableParam dataTableParam, Long tenantId);

    void updateStatus(List<Long> ids, Integer action);

    void repasswd(List<Long> ids, String passwd);

    void updateLanguage(Long id, String language);

    User getUserByToken(String token);

}

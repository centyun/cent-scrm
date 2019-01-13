package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.domain.User;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface UserMapper {

    void addUser(User user);

    void updateUser(User user);

    User getUserById(@Param("id") Long userId);

    void addMainUser(User user);

    List<User> getPageUsers(@Param("tenantId") Long tenantId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);

    void updateStatus(@Param("ids") List<Long> ids, @Param("status") int status, @Param("editor") Long editor);

    void repasswd(@Param("ids") List<Long> ids, @Param("passwd") String passwd);

    int getUserByName(User user);

    void updateLanguage(@Param("id") Long id, @Param("language") String language);

    User getUserByLoginName(@Param("loginName") String loginName);

}

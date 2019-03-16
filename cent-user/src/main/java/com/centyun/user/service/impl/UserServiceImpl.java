package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.core.domain.Administrator;
import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.core.util.encode.AesCryptUtils;
import com.centyun.user.constant.UserConstant;
import com.centyun.user.mapper.UserMapper;
import com.centyun.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public void saveUser(User user) {
        Administrator administrator = (Administrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (administrator == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        if (checkUser(user)) {
            throw new BadRequestException(UserConstant.USER_EXISTED);
        }
        String id = user.getId();
        if (StringUtils.isEmpty(id)) {
            SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(UserConstant.DATACENTER_ID, UserConstant.MACHINE_ID);
            user.setId(snowFlake.nextId());
            user.setCreator(administrator.getId());
            userMapper.addUser(user);
        } else {
            user.setEditor(administrator.getId());
            userMapper.updateUser(user);
        }
    }

    private boolean checkUser(User user) {
        int count = userMapper.getUserByName(user);
        return count > 0;
    }

    @Override
    public PageInfo<User> getPageUsers(DataTableParam dataTableParam, String tenantId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<User> users = userMapper.getPageUsers(tenantId, StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public void updateStatus(List<String> ids, Integer action) {
        Administrator administrator = (Administrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (administrator == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        // action转换状态: 注销0转换为已注销4, 启用1转换为已注册0
        Integer status = action == 0 ? UserConstant.USER_STATUS_DELETED : UserConstant.USER_STATUS_REGISTED;
        userMapper.updateStatus(ids, status, administrator.getId());
    }

    @Override
    public void repasswd(List<String> ids, String passwd) {
        Administrator administrator = (Administrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (administrator == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        userMapper.repasswd(ids, passwd);
    }

    @Override
    public void updateLanguage(String id, String language) {
        userMapper.updateLanguage(id, language);
    }

    @Override
    public User getUserByToken(String token) {
        String loginName = AesCryptUtils.getInstance().decryptAes(token);
        return userMapper.getUserByLoginName(loginName);
    }

}

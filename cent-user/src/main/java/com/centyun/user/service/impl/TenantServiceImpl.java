package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.core.util.encode.EncryptUtils;
import com.centyun.user.constant.UserConstant;
import com.centyun.user.domain.Manager;
import com.centyun.user.domain.Tenant;
import com.centyun.user.mapper.TenantMapper;
import com.centyun.user.mapper.UserMapper;
import com.centyun.user.service.TenantService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantMapper tenantMapper;
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Tenant> getTenants(DataTableParam dataTableParam) {
        PageHelper.startPage(dataTableParam.getStart(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Tenant> tenants = tenantMapper.getTenants(StringUtils.isEmpty(searchValue) ? null: searchValue, 
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Tenant> pageInfo = new PageInfo<>(tenants);
        return pageInfo;
    }

    @Override
    public List<KeyValuePair> getAllTenants() {
        List<KeyValuePair> tenants = tenantMapper.getAllTenants();
        return tenants;
    }

    @Override
    public void saveTenant(Tenant tenant) {
        // 获取当前用户
        Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(manager == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        
        if(checkTenant(tenant)) {
            throw new BadRequestException(UserConstant.TENANT_EXISTED);
        }
        Long id = tenant.getId();
        if(id == null || id <= 0) {
            SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(UserConstant.DATACENTER_ID, UserConstant.MACHINE_ID);
            id = snowFlake.nextId();
            tenant.setId(id);
            tenant.setCreator(manager.getId());
            tenantMapper.addTenant(tenant);
            User user = new User(snowFlake.nextId(), id, tenant.getMainUser(), manager.getId());
            user.setPassword(EncryptUtils.encrypt(tenant.getMainUserPwd()));
            user.setMobile(tenant.getMobile());
            user.setPhone(tenant.getPhone());
            user.setEmail(tenant.getEmail());
            userMapper.addMainUser(user);
        } else {
            tenant.setEditor(manager.getId());
            tenantMapper.updateTenant(tenant);
        }
    }

    private boolean checkTenant(Tenant tenant) {
        int count = tenantMapper.getTenantByName(tenant);
        return count > 0;
    }

    @Override
    public Tenant getTenantById(Long id) {
        return tenantMapper.getTenantById(id);
    }

    @Override
    public void updateStatus(List<Long> ids, Integer action) {
        Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(manager == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        // action转换状态: 注销0转换为已注销4, 启用1转换为已注册0
        Integer status = action == 0 ? UserConstant.TENANT_STATUS_DELETED : UserConstant.TENANT_STATUS_REGISTED;
        tenantMapper.updateStatus(ids, status, manager.getId());
    }

}

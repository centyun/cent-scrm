package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.core.domain.Administrator;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.user.constant.UserConstant;
import com.centyun.user.domain.Charge;
import com.centyun.user.mapper.ChargeMapper;
import com.centyun.user.service.ChargeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeMapper chargeMapper;

    @Override
    public PageInfo<Charge> getPageCharges(DataTableParam dataTableParam, String tenantId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Charge> charges = chargeMapper.getPageCharges(tenantId, StringUtils.isEmpty(searchValue) ? null: searchValue, 
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Charge> pageInfo = new PageInfo<>(charges);
        return pageInfo;
    }

    @Override
    public Charge getChargeById(String id) {
        return chargeMapper.getChargeById(id);
    }

    @Override
    public void saveCharge(Charge charge) throws Exception {
        // 获取当前用户
        Administrator administrator = (Administrator)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(administrator == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(UserConstant.DATACENTER_ID, UserConstant.MACHINE_ID);
        charge.setId(snowFlake.nextId());
        charge.setChargeAdministrator(administrator.getId());
        chargeMapper.addCharge(charge);
    }

    @Override
    public void updateStatus(List<String> ids, Integer action) throws Exception {
        Administrator administrator = (Administrator)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(administrator == null) {
            throw new BadRequestException(UserConstant.AUTH_FAIL);
        }
        // action转换状态: 注销0转换为下线停用, 启用1转换为正常
        Integer status = action == 0 ? UserConstant.CHARGE_STATUS_DELETED : UserConstant.CHARGE_STATUS_OK;
        chargeMapper.updateStatus(ids, status, administrator.getId());
    }

}

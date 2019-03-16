package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.user.constant.UserConstant;
import com.centyun.user.domain.Consume;
import com.centyun.user.mapper.ConsumeMapper;
import com.centyun.user.service.ConsumeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ConsumeServiceImpl implements ConsumeService {

    @Autowired
    private ConsumeMapper consumeMapper;

    @Override
    public PageInfo<Consume> getPageConsumes(DataTableParam dataTableParam, String tenantId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Consume> consumes = consumeMapper.getPageConsumes(tenantId, StringUtils.isEmpty(searchValue) ? null: searchValue, 
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Consume> pageInfo = new PageInfo<>(consumes);
        return pageInfo;
    }

    @Override
    public Consume getConsumeById(String id) {
        return consumeMapper.getConsumeById(id);
    }

    @Override
    public void saveConsume(Consume consume) {
        // 获取当前用户
        SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(UserConstant.DATACENTER_ID, UserConstant.MACHINE_ID);
        consume.setId(snowFlake.nextId());
        consumeMapper.addConsume(consume);
    }

}

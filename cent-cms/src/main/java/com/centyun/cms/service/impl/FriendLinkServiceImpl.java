package com.centyun.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.cms.constant.SieConstant;
import com.centyun.cms.domain.FriendLink;
import com.centyun.cms.mapper.FriendLinkMapper;
import com.centyun.cms.service.FriendLinkService;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {
    
    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public void saveFriendLink(FriendLink friendLink) {
        if(friendLink != null && friendLink.getTenantId() != null) {
            Long id = friendLink.getId();
            if (id == null || id <= 0) {
                SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(SieConstant.DATACENTER_ID, SieConstant.MACHINE_ID);
                friendLink.setId(snowFlake.nextId());
                friendLinkMapper.addFriendLink(friendLink);
            } else {
                friendLinkMapper.updateFriendLink(friendLink);
            }
        }
    }

    @Override
    public FriendLink getFriendLink(Long tenantId) {
        return friendLinkMapper.getFriendLink(tenantId);
    }

    @Override
    public PageInfo<FriendLink> getPageFriendLinks(DataTableParam dataTableParam, Long tenantId) {
        PageHelper.startPage(dataTableParam.getStart(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<FriendLink> friendLinks = friendLinkMapper.getPageFriendLinks(tenantId, StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<FriendLink> pageInfo = new PageInfo<>(friendLinks);
        return pageInfo;
    }

}

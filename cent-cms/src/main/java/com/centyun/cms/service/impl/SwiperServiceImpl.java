package com.centyun.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.cms.constant.SieConstant;
import com.centyun.cms.domain.Swiper;
import com.centyun.cms.mapper.SwiperMapper;
import com.centyun.cms.service.SwiperService;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SwiperServiceImpl implements SwiperService {
    
    @Autowired
    private SwiperMapper swiperMapper;

    @Override
    public void saveSwiper(Swiper swiper) {
        if(swiper != null && swiper.getTenantId() != null) {
            String id = swiper.getId();
            if (StringUtils.isEmpty(id)) {
                SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(SieConstant.DATACENTER_ID, SieConstant.MACHINE_ID);
                swiper.setId(snowFlake.nextId());
                swiperMapper.addSwiper(swiper);
            } else {
                swiperMapper.updateSwiper(swiper);
            }
        }
    }

    @Override
    public Swiper getSwiper(String tenantId, String siteId) {
        return swiperMapper.getSwiper(tenantId);
    }

    @Override
    public PageInfo<Swiper> getPageSwipers(DataTableParam dataTableParam, String tenantId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Swiper> swiperrs = swiperMapper.getPageSwipers(tenantId, StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Swiper> pageInfo = new PageInfo<>(swiperrs);
        return pageInfo;
    }

}

package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.user.domain.IpAddress;
import com.centyun.user.mapper.IpAddressMapper;
import com.centyun.user.service.IpAddressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IpAddressServiceImpl implements IpAddressService {

    @Autowired
    private IpAddressMapper ipAddressMapper;

    @Override
    public PageInfo<IpAddress> listPageIpAddresses(DataTableParam dataTableParam, String provinceId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<IpAddress> ips = ipAddressMapper.listPageIpAddresses(provinceId, StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<IpAddress> pageInfo = new PageInfo<>(ips);
        return pageInfo;
    }

    @Override
    public IpAddress getIpAddressById(Long id) {
        return ipAddressMapper.getIpAddressById(id);
    }

    @Override
    public void addIpAddress(IpAddress ipAddress) {
        ipAddressMapper.addIpAddress(ipAddress);
    }

    @Override
    public List<IpAddress> listIpAddressesByIds(List<Long> ids) {
        return ipAddressMapper.listIpAddressesByIds(ids);
    }

    @Override
    public int checkIpAddress(Long id) {
        return ipAddressMapper.checkIpAddress(id);
    }

    @Override
    public void updateIpAddress(IpAddress ipAddress) {
        ipAddressMapper.updateIpAddress(ipAddress);
    }

    @Override
    public List<IpAddress> listIpAddresses(Integer count, Integer status) {
        return ipAddressMapper.listIpAddressesByStatus(count, status);
    }

}

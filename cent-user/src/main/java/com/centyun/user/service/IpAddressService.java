package com.centyun.user.service;

import java.util.List;

import com.centyun.core.table.DataTableParam;
import com.centyun.user.domain.IpAddress;
import com.github.pagehelper.PageInfo;

public interface IpAddressService {
    
    PageInfo<IpAddress> listPageIpAddresses(DataTableParam dataTableParam, String provinceId);
    
    IpAddress getIpAddressById(Long id);
    
    void addIpAddress(IpAddress ipAddress);
    
    List<IpAddress> listIpAddressesByIds(List<Long> ids);
    
    int checkIpAddress(Long id);
    
    void updateIpAddress(IpAddress ipAddress);

    List<IpAddress> listIpAddresses(Integer count, Integer status);

}

package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.table.KeyValuePair;
import com.centyun.user.domain.IpAddress;

@Mapper
public interface IpAddressMapper {
    
    List<IpAddress> listPageIpAddresses(@Param("provinceId") String provinceId, @Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);
    
    IpAddress getIpAddressById(@Param("id") Long id);
    
    void addIpAddress(IpAddress ipAddress);
    
    List<IpAddress> listIpAddressesByIds(@Param("ids") List<Long> ids);
    
    int checkIpAddress(@Param("id") Long id);

    void updateIpAddress(IpAddress ipAddress);

    List<IpAddress> listIpAddressesByStatus(@Param("count") Integer count, @Param("status") Integer status);

}

package com.centyun.base.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.base.domain.WxAuth;

@Mapper
public interface WxAuthMapper {

    void addWxAuth(WxAuth wxAuth);

    void updateWxAuth(WxAuth wxAuth);

    WxAuth getWxAuthById(@Param("id") String id);

    WxAuth getWxAuthByTenantId(@Param("tenantId") String tenantId);

}

package com.centyun.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.cms.domain.FriendLink;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface FriendLinkMapper {
    
    void addFriendLink(FriendLink friendLink);
    
    void updateFriendLink(FriendLink friendLink);
    
    FriendLink getFriendLink(@Param("tenantId") String tenantId);

    List<FriendLink> getPageFriendLinks(@Param("tenantId") String tenantId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);

}

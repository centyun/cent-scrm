package com.centyun.cms.service;

import com.centyun.cms.domain.FriendLink;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface FriendLinkService {
    
    void saveFriendLink(FriendLink friendLink);
    
    FriendLink getFriendLink(Long tenantId);

    PageInfo<FriendLink> getPageFriendLinks(DataTableParam dataTableParam, Long tenantId);

}

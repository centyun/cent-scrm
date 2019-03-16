package com.centyun.base.service;

import com.centyun.base.domain.FaceGroup;
import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;

public interface FaceService {

    FaceGroup getGroupByTenantId(User user);
    
    void updateGroupStatus(String id);

    void saveFaceUser(User user, String name, String face, String base64Image) throws BadRequestException;

}

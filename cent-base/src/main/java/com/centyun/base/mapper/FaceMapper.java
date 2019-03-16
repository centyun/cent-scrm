package com.centyun.base.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.base.domain.FaceGroup;
import com.centyun.base.domain.FacePicture;
import com.centyun.base.domain.FaceUser;

@Mapper
public interface FaceMapper {

    FaceGroup getFaceGroupByTenantId(@Param("tenantId") String tenantId);

    void addFaceGroup(FaceGroup group);

    void updateGroupStatus(@Param("id") String id);

    FaceUser getFaceUserByUserName(@Param("name") String name, @Param("tenantId") String tenantId);

    void addFaceUser(FaceUser faceUser);

    void addFacePicture(FacePicture facePicture);

}

package com.centyun.base.service.impl;

import java.io.File;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.centyun.base.BaseApplication;
import com.centyun.base.constant.BaseConstant;
import com.centyun.base.domain.FaceGroup;
import com.centyun.base.domain.FacePicture;
import com.centyun.base.domain.FaceUser;
import com.centyun.base.mapper.FaceMapper;
import com.centyun.base.service.FaceService;
import com.centyun.base.util.FaceSearch;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.core.util.file.FileUploadUtils;

@Service
public class FaceServiceImpl implements FaceService {
    private Logger log = LoggerFactory.getLogger(FaceServiceImpl.class);

    @Autowired
    private FaceMapper faceMapper;

    @Value("${baidu.ai.appid}")
    private String appId;

    @Value("${baidu.ai.apikey}")
    private String apikey;

    @Value("${baidu.ai.secretkey}")
    private String secretkey;

    @Value("${UPLOAD_DIR}")
    private String uploadDir;

    @Value("${BASE_URL}")
    private String baseUrl;

    @Override
    public FaceGroup getGroupByTenantId(User user) {
        String tenantId = user.getTenantId();
        FaceGroup group = faceMapper.getFaceGroupByTenantId(tenantId);
        if(group == null || StringUtils.isEmpty(group.getId())) {
            group = new FaceGroup();
            group.setId(tenantId);
            group.setName(user.getTenantName());
            group.setCreator(user.getId());
            group.setCreatorName(user.getLoginName());
            faceMapper.addFaceGroup(group);
            log.info(tenantId + " group created in db");
        }
        return group;
    }

    @Override
    public void updateGroupStatus(String id) {
        faceMapper.updateGroupStatus(id);
        log.info(id + " group created in ai");
    }

    @Override
    public void saveFaceUser(User user, String name, String face, String base64Image) throws BadRequestException {
        FaceUser faceUser = faceMapper.getFaceUserByUserName(name, user.getTenantId());
        SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(BaseConstant.DATACENTER_ID, BaseConstant.MACHINE_ID);
        if(faceUser == null) {
            faceUser = new FaceUser();
            faceUser.setId(snowFlake.nextId());
            faceUser.setGroupId(user.getTenantId());
            faceUser.setName(name);
            faceUser.setCreator(user.getId());
            faceUser.setCreatorName(user.getLoginName());
            faceMapper.addFaceUser(faceUser);
        }
        
        String userId = faceUser.getId();
        if(!StringUtils.isEmpty(face)) {
            String pictureId = snowFlake.nextId();
            addPicture(user, faceUser, pictureId, face);
            // 调用百度API在百度人脸库注册人脸
            JSONObject userObj = FaceSearch.createUser(appId, apikey, secretkey, user.getTenantId(), userId, name, face, FaceSearch.URL_IMAGE);
            int errorCode = userObj.getInt("error_code");
            if(errorCode != 0) {
                throw new BadRequestException("face." + errorCode);
            }
        }
        if(!StringUtils.isEmpty(base64Image)) {
            String pictureId = snowFlake.nextId();
            String image = base64ToImage(base64Image);
            addPicture(user, faceUser, pictureId, image);
            // 调用百度API在百度人脸库注册人脸
            JSONObject userObj = FaceSearch.createUser(appId, apikey, secretkey, user.getTenantId(), userId, name, base64Image, FaceSearch.BASE64_IMAGE);
            int errorCode = userObj.getInt("error_code");
            if(errorCode != 0) {
                throw new BadRequestException("face." + errorCode);
            }
        }
        
    }

    private void addPicture(User user, FaceUser faceUser, String id, String image) {
        FacePicture facePicture = new FacePicture();
        facePicture.setId(id);
        facePicture.setFaceUserId(faceUser.getId());
        facePicture.setPicture(image);
        facePicture.setCreator(user.getId());
        facePicture.setCreatorName(user.getLoginName());
        faceMapper.addFacePicture(facePicture);
    }
    
    private String base64ToImage(String base64Image) {
        File saveFile = FileUploadUtils.saveBase64Image(base64Image, uploadDir, BaseApplication.CODE);
        String fileUrl = baseUrl + AppConstant.UPLOAD_DIR
                + saveFile.getAbsolutePath().substring(uploadDir.length());
        return fileUrl;
    }

}

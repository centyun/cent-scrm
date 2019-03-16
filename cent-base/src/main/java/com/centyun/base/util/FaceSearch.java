package com.centyun.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.baidu.aip.face.AipFace;
import com.centyun.core.domain.ResultEntity;

public class FaceSearch {
    
    public static final String URL_IMAGE = "URL";
    public static final String BASE64_IMAGE = "BASE64";

    public static ResultEntity search(String base64Image, String appId, String apikey, String secretkey, String groupIdList) {
//        saveImage(base64Image);
        // 初始化一个AipFace
        AipFace client = new AipFace(appId, apikey, secretkey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("max_user_num", "3");

        String imageType = "BASE64";
        
        // 调用接口
        org.json.JSONObject search = client.search(base64Image, imageType, groupIdList, options);
        System.out.println(search.toString(2));
        
        ResultEntity result = new ResultEntity();
        int errorCode = search.getInt("error_code");
        if(errorCode == 0) {
            result.setStatus(HttpStatus.OK.value());
            JSONObject userJson = search.getJSONObject("result");
            JSONArray userList = userJson.getJSONArray("user_list");
            for (Object userObject : userList) {
                org.json.JSONObject userMap = (org.json.JSONObject)userObject;
                int score = userMap.getInt("score");
                String userName = userMap.getString("user_info");
                if(score >= 80) {
                    result.setData(StringUtils.isEmpty(userName) ? userMap.getString("user_id") : userName);
                    break;
                } else {
                    result.setData((StringUtils.isEmpty(userName) ? userMap.getString("user_id") : userName) + ", 但是识别度只有" + score);
                    break;
                }
            }
        } else {
            System.out.println("error===" + search.getString("error_msg"));
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setData(errorCode);
        }
        return result;
    }

    public static JSONObject createGroup(String appId, String apikey, String secretkey, String groupId) {
        // 初始化一个AipFace
        AipFace client = new AipFace(appId, apikey, secretkey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        
        // 创建用户组
        JSONObject res = client.groupAdd(groupId, options);
        System.out.println(res.toString(2));
        return res;
    }
    
    public static void deleteGroup(String appId, String apikey, String secretkey, String groupId) {
        // 初始化一个AipFace
        AipFace client = new AipFace(appId, apikey, secretkey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        HashMap<String, String> options = new HashMap<String, String>();
        
        // 删除用户组
        JSONObject res = client.groupDelete(groupId, options);
        System.out.println(res.toString(2));
    }
    
    public static JSONObject createUser(String appId, String apikey, String secretkey
            , String groupId, String userId, String userName, String image, String imageType) {
        // 初始化一个AipFace
        AipFace client = new AipFace(appId, apikey, secretkey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", userName);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        
        // 人脸注册
        JSONObject res = client.addUser(image, imageType, groupId, userId, options);
        System.out.println("addUser====" + res.toString(2));
        return res;
    }
    
    public static void listUsers(String appId, String apikey, String secretkey
            , String groupId) {
        // 初始化一个AipFace
        AipFace client = new AipFace(appId, apikey, secretkey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("start", "0");
        options.put("length", "50");
        
        // 人脸注册
        JSONObject res = client.getGroupUsers(groupId, options);
        System.out.println(res.toString(2));
    }
    
    public static void getUser(String appId, String apikey, String secretkey
            , String groupId, String userId) {
        // 初始化一个AipFace
        AipFace client = new AipFace(appId, apikey, secretkey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        
        // 人脸注册
        JSONObject res = client.getUser(userId, groupId, options);
        System.out.println(res.toString(2));
    }

    private static void saveImage(String imageData) {
      FileOutputStream output = null;
        try {
            File file = new File("E:/release/face/images/" + System.currentTimeMillis() + ".jpg");
            output = new FileOutputStream(file);
            byte[] data = Base64.decodeBase64(imageData);
            output.write(data);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        String appId = "16152822";
        String apikey = "M7TlD0h2LxBoVqTSt2qQWYzo";
        String secretkey = "6uGME43gAl3Q904qj3iDvKp9NzC1bKei";
        String groupId = "40932672595296256";
        String userId = "hello2";
/*        String userName = "张三";
        String image = "https://cms6.coolgua.com/zhou.jpg";
        String imageType = "URL";
        createUser(appId, apikey, secretkey, groupId, userId, userName, image, imageType);

        image = "https://cms6.coolgua.com/yin.jpg";
        userName = "李四";
        createUser(appId, apikey, secretkey, groupId, userId, userName, image, imageType);
        */
//        getUser(appId, apikey, secretkey, groupId, userId);
        deleteGroup(appId, apikey, secretkey, groupId);
    }

}

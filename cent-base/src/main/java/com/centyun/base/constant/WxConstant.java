package com.centyun.base.constant;

/**
 * 微信相关的常量
 * @author yinww
 *
 */
public interface WxConstant {
    
    String APP_ID = "app_id";
    
    String APP_SECRET = "app_secret";

    String AUTH_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    
    String OPNEID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    
}

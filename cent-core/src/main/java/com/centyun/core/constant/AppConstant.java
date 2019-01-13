package com.centyun.core.constant;

/**
 * 全局的常量
 * @author yinww
 *
 */

public interface AppConstant {
    
    // constants of centyun sub-system
    String SERVICES = "centyun-services";   // 服务中心
    String USER = "centyun-user"; // 用户
    String CONSOLE = "centyun-console";   // 控制台
    String MAIL = "centyun-mail"; // 邮件
    String SMS = "centyun-sms";   // 短信
    String TRACER = "centyun-tracer";   // 溯源
    
    String CMS = "centyun-cms";   // 网站
    String ACCOUNT = "centyun-account";   // 财务
    String CRM = "centyun-crm";   // CRM

    String UTF8 = "UTF-8";
    String LOCALHOST = "localhost";
    String EMPTY = "";
    String DOT = ".";
    String COMMA = ",";
    String URL_SEPARATOR = "/";
    String UPLOAD_DIR = "upload/";
    
    String TOKEN = "cty_token";
    String LOGIN_USER = "cty_user";
    
    int DEFAULT_PAGE_START = 1;
    int DEFAULT_PAGE_SIZE = 10;
    
    // file ext
    String REG_FILE_EXT = "^.*?\\.(jpg|jpeg|bmp|gif|png|docx|doc|xlsx|xls|txt|zip|pptx|ppt|rar)$";
    String REG_EXEL_EXT = "^.*?\\.(xlsx|xls)$";
    String REG_CONTENT_EXT = "^.*?\\.(txt|html|htm|zip)$";
    String REG_IMG_EXT = "^.*?\\.(jpg|jpeg|bmp|gif|png)$";

}

package com.centyun.user.constant;

public interface UserConstant {
    String AUTH_FAIL = "common.auth_fail";

    String TENANT_EXISTED = "tenant.existed";
    String USER_EXISTED = "user.existed";
    String PRODUCT_EXISTED = "product.existed";
    
    long DATACENTER_ID = 0; // 雪花算法的数据中心id
    long MACHINE_ID = 0; // 雪花算法的机器id

    int CHARGE_STATUS_OK = 1; // 充值成功
    int CHARGE_STATUS_DELETED = 2; // 取消充值

    int TENANT_STATUS_REGISTED = 0; // 0已注册
    int TENANT_STATUS_AUDITED = 1; // 1已审核
    int TENANT_STATUS_CERTIFIED = 2; // 2已认证
    int TENANT_STATUS_LOCKED = 3; // 3已冻结
    int TENANT_STATUS_DELETED = 4; // 4已注销

    int USER_STATUS_REGISTED = 0; // 0已注册
    int USER_STATUS_AUDITED = 1; // 1已审核
    int USER_STATUS_CERTIFIED = 2; // 2已认证
    int USER_STATUS_LOCKED = 3; // 3已冻结
    int USER_STATUS_DELETED = 4; // 4已注销

    int PRODUCT_STATUS_OK = 1; // 正常
    int PRODUCT_STATUS_OFFLINE = 2; // 下线停用
    int PRODUCT_STATUS_DEPRECATED = 3; // 可用，但已有新版
}

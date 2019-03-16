-- create database ct_user;
use ct_user;

/*==============================================================
   Table: us_tenant 租户
==============================================================*/
create table us_tenant
(
   id                   varchar(20) not null,
   name                 varchar(128) not null,
   code                 varchar(64),
   main_user            varchar(32),
   contact              varchar(64),
   mobile               varchar(32),
   phone                varchar(32),
   email                varchar(64),
   address              varchar(128),
   logo                 varchar(128),
   type                 tinyint comment '0个人, 1企业, 2个体工商户, 3政府, 4媒体, 5其他组织',
   status               tinyint comment '0已注册, 1已审核, 2已认证, 3已冻结, 4已注销',
   note                 varchar(256),
   access_key           varchar(64),
   creator              varchar(20),
   create_time          datetime,
   editor               varchar(20),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_user 用户/账号
==============================================================*/
create table us_user
(
   id                   varchar(20) not null,
   tenant_id            varchar(20),
   login_name           varchar(32) not null,
   type                 tinyint comment '0子账号, 1主账号',
   password             varchar(108),
   display_name         varchar(128),
   real_name            varchar(128),
   mobile               varchar(32),
   phone                varchar(32),
   email                varchar(64),
   head_image           varchar(128),
   gender               tinyint comment '1男, 0女',
   status               tinyint comment '0已注册, 1已审核, 2已认证, 3已冻结, 4已注销',
   grade                tinyint comment '0初级, 1....',
   language             varchar(8),
   creator              varchar(20),
   create_time          datetime,
   editor               varchar(20),
   edit_time            datetime,
   password_time        datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_administrator 管理员
==============================================================*/
create table us_administrator
(
   id                   varchar(20) not null,
   login_name           varchar(32) not null,
   password             varchar(108) not null,
   display_name         varchar(32) not null,
   phone                varchar(32),
   email                varchar(64),
   language             varchar(8),
   status               tinyint,
   creator              varchar(20),
   create_time          datetime,
   editor               varchar(20),
   edit_time            datetime,
   password_time        datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_admin_menu 管理菜单
==============================================================*/
create table us_admin_menu
(
   id                   varchar(20) not null,
   name                 varchar(64),
   english_name         varchar(128),
   icon                 varchar(32),
   code                 varchar(32),
   url                  varchar(128),
   order_no             smallint,
   status               tinyint,
   create_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_product 产品
==============================================================*/
create table us_product
(
   id                   varchar(20) not null,
   name                 varchar(64) not null,
   english_name         varchar(128),
   code                 varchar(64),
   icon                 varchar(32),
   version              varchar(32),
   release_url          varchar(64),
   release_time         datetime,
   product_manager      varchar(64),
   note                 varchar(256),
   order_no             smallint,
   status               tinyint comment '0未发布，1正常，2下线停用，3将停用',
   creator              varchar(20),
   create_time          datetime,
   editor               varchar(20),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_charge 充值
==============================================================*/
create table us_charge
(
   id                   varchar(20) not null,
   tenant_id            varchar(32) not null,
   product_id           varchar(32) not null,
   money                decimal(12,2),
   quota                int,
   single_money         decimal(12,2),
   expired_time         datetime,
   note                 varchar(256),
   status               tinyint comment '1 充值成功 2 取消充值',
   sales                varchar(32),
   charge_administrator varchar(20),
   charge_time          datetime,
   editor               varchar(20),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_audit 审计日志
==============================================================*/
create table us_audit
(
   id                   varchar(20) not null,
   action               varchar(64),
   module               varchar(32),
   content              text,
   ip                   bigint,
   operator             varchar(20),
   operate_time         datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_consume 消费记录
==============================================================*/
create table us_consume
(
   id                   varchar(20) not null,
   tenant_id            varchar(20) not null,
   product_id           varchar(20) not null,
   user_id              varchar(20),
   amount               int,
   money                decimal(12,2),
   occur_time           datetime,
   ip                   bigint,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_module 应用模块
==============================================================*/
create table us_module
(
   id                   varchar(20) not null,
   product_id           varchar(20),
   parent_id            varchar(20),
   name                 varchar(64),
   english_name         varchar(128),
   icon                 varchar(32),
   code                 varchar(32),
   url                  varchar(128),
   order_no             smallint,
   status               tinyint,
   create_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_continent 大洲
==============================================================*/
create table us_continent
(
   id                   varchar(32) not null,
   name                 varchar(64),
   english_name         varchar(64),
   order_no             smallint,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_country_region 国家和地区
==============================================================*/
create table us_country_region
(
   id                   varchar(32) not null,
   continent_id         varchar(32) not null,
   name                 varchar(64) not null,
   english_name         varchar(64),
   code                 varchar(32),
   area_code            varchar(32),
   pinyin               varchar(64),
   pinyin_short         varchar(32),
   order_no             int,
   creator              varchar(20),
   create_time          datetime,
   editor               varchar(20),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_province 省
==============================================================*/
create table us_province
(
   id                   varchar(32) not null,
   country_region_id    varchar(32) not null,
   name                 varchar(64) not null,
   english_name         varchar(64),
   code                 varchar(32),
   area_code            varchar(32),
   pinyin               varchar(64),
   pinyin_short         varchar(32),
   order_no             int,
   creator              varchar(20),
   create_time          datetime,
   editor               varchar(20),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_city 市
==============================================================*/
create table us_city
(
   id                   varchar(32) not null,
   province_id          varchar(32) not null,
   name                 varchar(64) not null,
   english_name         varchar(64),
   code                 varchar(32),
   area_code            varchar(32),
   pinyin               varchar(64),
   pinyin_short         varchar(32),
   order_no             int,
   creator              varchar(20),
   create_time          datetime,
   editor               varchar(20),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_ip_address IP地址
==============================================================*/
create table us_ip_address
(
   id                   bigint not null comment 'ipv4对应的长整数',
   ipv4                 varchar(15),
   isp                  varchar(32) comment '网络运营商',
   isp_en               varchar(255),
   lon                  decimal(8,4) comment '经度',
   lat                  decimal(8,4) comment '纬度',
   timezone             varchar(32),
   country_region_id    varchar(32),
   province_id          varchar(32),
   city_id              varchar(32),
   country_region_name  varchar(64),
   province_name        varchar(64),
   city_name            varchar(64),
   status               tinyint default 0 comment '0 初始入库, 1 对比了淘宝ip库, 2 对比了ip-api库, 3 校验完了前面两个ip库',
   create_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

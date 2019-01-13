create database ct_user;
use ct_user;

/*==============================================================
   Table: us_tenant 租户
==============================================================*/
create table us_tenant
(
   id                   bigint not null,
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
   creator              bigint,
   create_time          datetime,
   editor               bigint,
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
   id                   bigint not null,
   tenant_id            bigint,
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
   creator              bigint,
   create_time          datetime,
   editor               bigint,
   edit_time            datetime,
   password_time        datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_manager 管理员
==============================================================*/
create table us_manager
(
   id                   bigint not null,
   login_name           varchar(32) not null,
   password             varchar(108) not null,
   display_name         varchar(32) not null,
   phone                varchar(32),
   email                varchar(64),
   language             varchar(8),
   status               tinyint,
   creator              bigint,
   create_time          datetime,
   editor               bigint,
   edit_time            datetime,
   password_time        datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
   Table: us_module 模块
==============================================================*/
create table us_module
(
   id                   bigint not null,
   parent_id            bigint,
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
   id                   bigint not null,
   name                 varchar(64) not null,
   english_name         varchar(128),
   code                 varchar(64),
   icon                 varchar(32),
   version              varchar(32),
   release_url          varchar(64),
   release_time         datetime,
   product_manager      varchar(64),
   note                 varchar(256),
   status               tinyint comment '1正常，2下线停用，3升级后成了旧版，但仍然可以，4升级成后了旧版，不再可以',
   creator              bigint,
   create_time          datetime,
   editor               bigint,
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
   id                   bigint not null,
   tenant_id            varchar(32) not null,
   product_id           varchar(32) not null,
   money                decimal(12,2),
   quota                int,
   single_money         decimal(12,2),
   expired_time         datetime,
   note                 varchar(256),
   status               tinyint comment '1 充值成功 2 取消充值',
   sales                varchar(32),
   charge_manager       bigint,
   charge_time          datetime,
   editor               bigint,
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
   id                   bigint not null,
   action               varchar(64),
   module               varchar(32),
   content              text,
   ip                   bigint,
   operator             bigint,
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
   id                   bigint not null,
   tenant_id            bigint not null,
   product_id           bigint not null,
   user_id              bigint,
   amount               int,
   money                decimal(12,2),
   occur_time           datetime,
   ip                   bigint,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;


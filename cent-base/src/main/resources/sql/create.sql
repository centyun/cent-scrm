/*==============================================================
 Table: bs_dwz  短网址
==============================================================*/
create table bs_dwz
(
   id                   varchar(20) not null,
   long_url             varchar(256) not null,
   code                 varchar(8) not null,
   short_url            varchar(64),
   qrcode               varchar(128),
   tenant_id            varchar(20),
   tenant_name          varchar(128),
   creator              varchar(20),
   creator_name         varchar(128),
   create_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

CREATE INDEX idx_dwz_url ON bs_dwz (long_url);
CREATE INDEX idx_dwz_code ON bs_dwz (code);

/*==============================================================
 Table: bs_wx_auth  微信授权配置
==============================================================*/
create table bs_wx_auth
(
   id                   varchar(20) not null,
   app_id               varchar(32),
   app_secret           varchar(64),
   tenant_id            varchar(20),
   tenant_name          varchar(128),
   creator              varchar(20),
   creator_name         varchar(128),
   create_time          datetime,
   editor               varchar(20),
   editor_name          varchar(128),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: bs_face_group  人脸识别用户组
==============================================================*/
create table bs_face_group
(
   id                   varchar(20) not null comment 'tenantId',
   name                 varchar(64) comment 'tenant_name',
   status               tinyint default 0 comment '0 人脸库中创建了该组, 1 人脸库中未创建该组',
   creator              varchar(20),
   creator_name         varchar(128),
   create_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: bs_face_user  人脸识别用户
==============================================================*/
create table bs_face_user
(
   id                   varchar(20) not null,
   group_id             varchar(20),
   name                 varchar(64),
   creator              varchar(20),
   creator_name         varchar(128),
   create_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: bs_face_picture  人脸识别用户头像
==============================================================*/
create table bs_face_picture
(
   id                   varchar(20) not null,
   face_user_id         varchar(20),
   picture              varchar(256),
   creator              varchar(20),
   creator_name         varchar(128),
   upload_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

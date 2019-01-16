/*==============================================================
 Table: cm_module  模块
==============================================================*/
create table cm_module
(
   id                   bigint,
   parent_id            bigint,
   name                 varchar(64),
   english_name         varchar(128),
   icon                 varchar(32),
   code                 varchar(32),
   url                  varchar(128),
   status               tinyint,
   order_no             tinyint,
   create_time          datetime
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_site  站点
==============================================================*/
create table cm_site
(
   id                   bigint not null,
   tenant_id            bigint not null,
   name                 varchar(64),
   domain               varchar(128),
   other_domain         varchar(256),
   template             varchar(32),
   mobile_template      varchar(32),
   language             varchar(5),
   default_site         tinyint comment '0 不是默认, 1 是默认',
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_site_config  站点配置
==============================================================*/
create table cm_site_config
(
   site_id              bigint not null,
   tenant_id            bigint not null,
   logo                 varchar(128),
   logo_width           smallint,
   logo_height          smallint,
   pc_banner            varchar(128),
   mobile_banner        varchar(128),
   background           varchar(128),
   phone                varchar(32),
   qq                   varchar(32),
   email                varchar(64),
   web_qrcode           varchar(128),
   wxmp_qrcode          varchar(128),
   copyright            varchar(128),
   icp                  varchar(256),
   seo_title            varchar(256),
   seo_keyword          varchar(256),
   seo_note             varchar(256),
   trace_code           text,
   primary key (site_id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_site_parameter  站点参数
==============================================================*/
create table cm_site_parameter
(
   site_id              bigint not null,
   tenant_id            bigint not null,
   name                 varchar(64),
   parameter            varchar(64),
   value                varchar(128),
   primary key (site_id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_friend_link  友情链接
==============================================================*/
create table cm_friend_link
(
   id                   bigint not null,
   tenant_id            bigint not null,
   group_name           varchar(64),
   link                 varchar(128),
   name                 varchar(64),
   picture              varchar(128),
   open_target          tinyint comment '0 在当前页打开, 1在新页签打开',
   order_no             tinyint,
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_swiper  轮播图
==============================================================*/
create table cm_swiper
(
   id                   bigint not null,
   tenant_id            bigint not null,
   group_name           varchar(64),
   title                varchar(128),
   picture              varchar(128),
   link                 varchar(128),
   open_target          tinyint comment '0 在当前页打开, 1在新页签打开',
   order_no             tinyint,
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_tag  标签
==============================================================*/
create table cm_tag
(
   id                   bigint not null,
   tenant_id            bigint not null,
   site_id              bigint,
   name                 varchar(64),
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_navigation  导航
==============================================================*/
create table cm_navigation
(
   id                   bigint not null,
   tenant_id            bigint not null,
   site_id              bigint not null,
   name                 varchar(64),
   type                 tinyint comment '1 页面, 2 文章列表, 3 导航列表, 4 图片列表, 5 链接',
   style                tinyint comment '1 列表, 2 网格',
   link                 varchar(128),
   open_target          tinyint,
   pc_parent_id         bigint,
   pc_display           tinyint comment '0 不显示, 1 显示',
   pc_level             tinyint,
   pc_order_no          tinyint,
   pc_cover_image       varchar(128),
   description          varchar(256),
   mobile_parent_id     bigint,
   mobile_display       tinyint,
   mobile_level         tinyint,
   mobile_order_no      tinyint,
   mobile_cover_image   varchar(128),
   author               varchar(64),
   publish_time         datetime,
   visit_num            bigint,
   seo_title            varchar(256),
   seo_keyword          varchar(256),
   seo_description      varchar(256),
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_article  文章
==============================================================*/
create table cm_article
(
   id                   bigint not null,
   tenant_id            bigint,
   site_id              bigint,
   nav_id               bigint,
   title                varchar(128),
   pc_display           tinyint,
   mobile_display       tinyint,
   to_top               tinyint,
   tags                 varchar(512),
   pc_content           longtext,
   mobile_content       longtext,
   main_image           varchar(128),
   thumbnail            varchar(128),
   author               varchar(64),
   publish_time         datetime,
   visit_num            bigint,
   seo_title            varchar(256),
   seo_keyword          varchar(256),
   seo_description      varchar(256),
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

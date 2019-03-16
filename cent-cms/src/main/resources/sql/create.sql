/*==============================================================
 Table: cm_site  站点
==============================================================*/
create table cm_site
(
   id                   varchar(20) not null,
   tenant_id            varchar(20) not null,
   name                 varchar(64),
   domain               varchar(128),
   other_domain         varchar(256),
   template             varchar(32),
   mobile_template      varchar(32),
   language             varchar(5),
   status               tinyint comment '0停用, 1启用',
   creator              varchar(20),
   creator_name         varchar(64),
   create_time          datetime,
   editor               varchar(20),
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_site_attribue  站点属性
==============================================================*/
create table cm_site_attribue
(
   site_id              varchar(20) not null,
   tenant_id            varchar(20) not null,
   logo                 varchar(128),
   logo_width           smallint,
   logo_height          smallint,
   banner               varchar(128),
   background           varchar(128),
   phone                varchar(32),
   qq                   varchar(32),
   email                varchar(64),
   web_qrcode           varchar(128),
   wx_qrcode            varchar(128),
   copyright            varchar(128),
   icp                  varchar(256),
   seo_title            varchar(256),
   seo_keyword          varchar(256),
   seo_description      varchar(256),
   trace_code           text,
   creator              varchar(20),
   creator_name         varchar(64),
   create_time          datetime,
   editor               varchar(20),
   editor_name          varchar(60),
   edit_time            datetime,
   primary key (site_id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_site_parameter  站点参数
==============================================================*/
create table cm_site_parameter
(
   site_id              varchar(20) not null,
   tenant_id            varchar(20) not null,
   name                 varchar(64),
   value                varchar(128),
   note                 varchar(64),
   creator              varchar(20),
   creator_name         varchar(64),
   create_time          datetime,
   editor               varchar(20),
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (site_id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_friend_link  友情链接
==============================================================*/
create table cm_friend_link
(
   id                   varchar(20) not null,
   tenant_id            varchar(20) not null,
   group_name           varchar(64),
   link                 varchar(128),
   name                 varchar(64),
   picture              varchar(128),
   open_target          tinyint comment '0 在当前页打开, 1在新页签打开',
   creator              varchar(20),
   creator_name         varchar(64),
   create_time          datetime,
   editor               varchar(20),
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
   id                   varchar(20) not null,
   tenant_id            varchar(20) not null,
   group_name           varchar(64),
   title                varchar(128),
   picture              varchar(128),
   link                 varchar(128),
   open_target          tinyint comment '0 在当前页打开, 1在新页签打开',
   creator              varchar(20),
   creator_name         varchar(64),
   create_time          datetime,
   editor               varchar(20),
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
   id                   varchar(20) not null,
   tenant_id            varchar(20) not null,
   site_id              varchar(20),
   name                 varchar(64),
   creator              varchar(20),
   creator_name         varchar(64),
   create_time          datetime,
   editor               varchar(20),
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
   id                   varchar(20) not null,
   tenant_id            varchar(20) not null,
   site_id              varchar(20) not null,
   name                 varchar(64),
   type                 tinyint comment '1 页面, 2 文章列表, 3 导航列表, 4 图片列表, 5 链接',
   style                tinyint comment '1 列表, 2 网格',
   short_link           varchar(20),
   external_link        varchar(128),
   open_target          tinyint,
   terminal             tinyint comment '0PC端, 1手机端',
   parent_id            varchar(20),
   display              tinyint comment '0 不显示, 1 显示',
   nav_level            tinyint,
   order_no             tinyint,
   cover_image          varchar(128),
   description          varchar(256),
   seo_title            varchar(256),
   seo_keyword          varchar(256),
   seo_description      varchar(256),
   creator              varchar(20),
   creator_name         varchar(64),
   create_time          datetime,
   editor               varchar(20),
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
   id                   varchar(20) not null,
   tenant_id            varchar(20),
   site_id              varchar(20),
   nav_id               varchar(20),
   title                varchar(128),
   display              tinyint comment '0不显示, 1显示',
   to_top               tinyint comment '0不置顶, 1置顶',
   tags                 varchar(512),
   content              longtext,
   main_image           varchar(128),
   thumbnail            varchar(128),
   author               varchar(64),
   publish_time         datetime,
   view_num             bigint,
   seo_title            varchar(256),
   seo_keyword          varchar(256),
   seo_description      varchar(256),
   creator              varchar(20),
   creator_name         varchar(64),
   create_time          datetime,
   editor               varchar(20),
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: cm_related_article  相关文章
==============================================================*/
create table cm_related_article
(
   id                   varchar(20) not null,
   nav_id               varchar(20),
   tenant_id            varchar(20),
   article_id           varchar(20),
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

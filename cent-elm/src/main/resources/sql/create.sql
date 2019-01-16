
/*==============================================================
 Table: lm_version 版本
 ==============================================================*/
create table lm_version
(
   id                   bigint not null,
   company_id           bigint,
   name                 varchar(64),
   english_name         varchar(128),
   creator              bigint,
   create_name          datetime,
   editor               bigint,
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;


/*==============================================================
 Table: ci_category 元器件分类
 ==============================================================*/
create table ci_category
(
   id                   bigint not null,
   project_id           bigint,
   parent_id            bigint,
   name                 varchar(64),
   code                 varchar(64),
   icon                 varchar(64),
   table_name           varchar(64),
   order_no             smallint,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;



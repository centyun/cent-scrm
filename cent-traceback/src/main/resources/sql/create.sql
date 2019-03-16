create database ct_traceback;
use ct_traceback;

/*==============================================================*/
/* table: tb_site                                               */
/*==============================================================*/
create table tb_site
(
   id                   bigint not null,
   tenant_id            bigint,
   name                 varchar(128) not null,
   domain               varchar(64),
   sub_domain           varchar(256),
   picture_code         varchar(512),
   text_code            varchar(512),
   nostyle_code         varchar(512),
   creator              bigint,
   creator_name         varchar(64),
   create_time          timestamp,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            timestamp,
   primary key (id)
)
engine = innodb
default charset = utf8;




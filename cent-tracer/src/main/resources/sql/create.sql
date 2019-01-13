create database ct_tracer;
use ct_tracer;

/*==============================================================*/
/* Table: TR_SITE                                               */
/*==============================================================*/
create table TR_SITE
(
   ID                   bigint not null,
   TENANT_ID            bigint,
   NAME                 varchar(128) not null,
   DOMAIN               varchar(64),
   SUB_DOMAIN           varchar(256),
   PICTURE_CODE         varchar(512),
   TEXT_CODE            varchar(512),
   NOSTYLE_CODE         varchar(512),
   CREATOR              bigint,
   CREATOR_NAME         varchar(64),
   CREATE_TIME          timestamp,
   EDITOR               bigint,
   EDITOR_NAME          varchar(64),
   EDIT_TIME            timestamp,
   primary key (ID)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;




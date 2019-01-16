/*============================================================
 Table: ml_mail  邮件
============================================================*/
create table ml_mail_0
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint comment '1 标准邮件, 2个性邮件',
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_1
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_2
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_3
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_4
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_5
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_6
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_7
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_8
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_9
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   reply_address        varchar(64),
   recipient            varchar(64),
   template_id          bigint,
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         text,
   unsubscribe          tinyint comment '0 不退订, 1 退订',
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

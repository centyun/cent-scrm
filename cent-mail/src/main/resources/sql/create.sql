
/*============================================================
 Table: ml_mail_package  邮包
============================================================*/
create table ml_mail_package
(
   id                   bigint,
   tenant_id            bigint,
   template_id          bigint,
   provider_id          tinyint,
   sender               varchar(64),
   sender_name          varchar(64),
   reply_address        varchar(64),
   send_to              varchar(64),
   subject              varchar(160),
   content              longtext,
   has_attachment       tinyint,
   scheduled            tinyint,
   scheduled_time       datetime,
   status               tinyint,
   campaign_id          varchar(40),
   mailing_id           varchar(40),
   label_id             int,
   unsubscribe          tinyint,
   unsubscribe_language varchar(5),
   unsubscribe_count    int,
   total                int,
   request_count        int,
   deliver_count        int,
   bounce_count         int,
   soft_bounce_count    int,
   invalid_address_count int,
   spam_count           int,
   repeat_address_count int,
   open_count           int,
   open_unique_count    int,
   click_count          int,
   click_unique_count   int,
   provider_exclude_count int,
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

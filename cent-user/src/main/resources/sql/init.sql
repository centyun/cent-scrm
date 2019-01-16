insert into us_manager (id, login_name, password, display_name, phone, email, language, status, create_time, password_time)
    values(1, 'sysadmin', 'iqc2vYSAdSPDNNzoc4OEMkC+AWOq46DsfKV6XJAltdoQgOdO17FMqQqO0rFkbL6D8cdB5jg9HCcQIlwq8lAk9JNsOJijL2Q59VAiOVy7mDA=', '系统管理员', '13718346788', 'test@hello.com', 'zh_CN', 1, now(), now());
   
insert into us_module (id, name, english_name, icon, code, url, order_no, status, create_time)
    values(1, '租户管理', 'Tenant Management', 'fa-user', 'tenant', '/tenant/index.html', 1, 1, now());
insert into us_module (id, name, english_name, icon, code, url, order_no, status, create_time)
    values(2, '账号管理', 'User Management', 'fa-users', 'user', '/user/index.html', 2, 1, now());
insert into us_module (id, name, english_name, icon, code, url, order_no, status, create_time)
    values(3, '产品管理', 'Product Management', 'fa-diamond', 'product', '/product/index.html', 3, 1, now());
insert into us_module (id, name, english_name, icon, code, url, order_no, status, create_time)
    values(4, '充值管理', 'Charge Management', 'fa-rmb', 'charge', '/charge/index.html', 4, 1, now());
insert into us_module (id, name, english_name, icon, code, url, order_no, status, create_time)
    values(5, '消费记录', 'Consume Record', 'fa-shopping-cart', 'consume', '/consume/index.html', 5, 1, now());
insert into us_module (id, name, english_name, icon, code, url, order_no, status, create_time)
    values(6, '日志管理', 'Audit Management', 'fa-file-text-o', 'audit', '/audit/index.html', 6, 0, now());


insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, status, creator, create_time)
    values(1, '财务管理软件', 'Account', 'account', 'fa-credit-card', '1.0', 'http://account.hello.com', now(), '张一龙', 1, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, status, creator, create_time)
    values(2, '内容管理系统', 'CMS', 'cms', 'fa-wikipedia-w', '1.0', 'http://cms.hello.com', now(), '张二龙', 1, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, status, creator, create_time)
    values(3, '用户行为溯源', 'Tracer', 'tracer', 'fa-chain', '1.0', 'http://tracer.hello.com', now(), '张三龙', 1, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, status, creator, create_time)
    values(4, '客户关系管理', 'CRM', 'crm', 'fa-gg', '1.0', 'http://crm.hello.com', now(), '张四龙', 1, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, status, creator, create_time)
    values(5, '邮件', 'Mail', 'dm', 'fa-envelope', '1.0', 'http://dm.hello.com', now(), '张五龙', 1, 1, now());

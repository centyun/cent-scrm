insert into us_administrator (id, login_name, password, display_name, phone, email, language, status, create_time, password_time)
    values('1', 'sysadmin', 'iqc2vYSAdSPDNNzoc4OEMkC+AWOq46DsfKV6XJAltdoQgOdO17FMqQqO0rFkbL6D8cdB5jg9HCcQIlwq8lAk9JNsOJijL2Q59VAiOVy7mDA=', '系统管理员', '13718346788', 'test@hello.com', 'zh_CN', 1, now(), now());

-- 管理菜单
insert into us_admin_menu (id, name, english_name, icon, code, url, order_no, status, create_time)
    values('1', '租户管理', 'Tenant Management', 'fa-user', 'tenant', '/tenant', 1, 1, now());
insert into us_admin_menu (id, name, english_name, icon, code, url, order_no, status, create_time)
    values('2', '账号管理', 'User Management', 'fa-users', 'user', '/user', 2, 1, now());
insert into us_admin_menu (id, name, english_name, icon, code, url, order_no, status, create_time)
    values('3', '产品管理', 'Product Management', 'fa-diamond', 'product', '/product', 3, 1, now());
insert into us_admin_menu (id, name, english_name, icon, code, url, order_no, status, create_time)
    values('4', '充值管理', 'Charge Management', 'fa-rmb', 'charge', '/charge', 4, 1, now());
insert into us_admin_menu (id, name, english_name, icon, code, url, order_no, status, create_time)
    values('5', '消费记录', 'Consume Record', 'fa-shopping-cart', 'consume', '/consume', 5, 1, now());
insert into us_admin_menu (id, name, english_name, icon, code, url, order_no, status, create_time)
    values('6', '位置服务', 'Location Service', ' fa-map-marker', 'location', '/location', 6, 1, now());
insert into us_admin_menu (id, name, english_name, icon, code, url, order_no, status, create_time)
    values('7', '日志管理', 'Audit Management', 'fa-file-text-o', 'audit', '/audit', 7, 0, now());

-- 初始产品
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, order_no, status, creator, create_time)
    values('01', '办公管理', 'OA', 'oa', 'fa-credit-card', '1.0', 'http://oa.hello.com', now(), '张一龙', 1, 0, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, order_no, status, creator, create_time)
    values('02', '客户关系管理', 'CRM', 'crm', 'fa-gg', '1.0', 'http://crm.hello.com', now(), '张四龙', 2, 0, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, order_no, status, creator, create_time)
    values('03', '财务管理', 'Account', 'account', 'fa-credit-card', '1.0', 'http://account.hello.com', now(), '张一龙', 1, 0, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, order_no, status, creator, create_time)
    values('04', '建站服务', 'CMS', 'cms', 'fa-wikipedia-w', '1.0', 'http://cms.hello.com', now(), '张二龙', 3, 1, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, order_no, status, creator, create_time)
    values('05', '活动', 'Campaign', 'cpn', ' fa-map-pin', '1.0', 'http://cpn.hello.com', now(), '张二龙', 4, 1, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, order_no, status, creator, create_time)
    values('06', '用户行为追溯', 'Trace Back', 'tb', 'fa-chain', '1.0', 'http://traceback.hello.com', now(), '张三龙', 5, 1, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, order_no, status, creator, create_time)
    values('07', '短信', 'SMS', 'sms', 'fa-slack', '1.0', 'http://sms.hello.com', now(), '张五龙', 6, 1, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, order_no, status, creator, create_time)
    values('08', '邮件', 'Mail', 'edm', 'fa-envelope', '1.0', 'http://edm.hello.com', now(), '张五龙', 7, 1, 1, now());
insert into us_product (id, name, english_name, code, icon, version, release_url, release_time, product_manager, order_no, status, creator, create_time)
    values('09', '基础服务', 'Base', 'bs', 'fa-arrows-alt', '1.0', 'http://base.hello.com', now(), '杨过', 8, 1, 1, now());

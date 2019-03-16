-- 控制台的模块(即各应用的菜单)
-- 一级模块1~99,二级模块100~9999, 三级模块10000~99999

-- 办公管理
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0101', '01', '资产管理', 'Asset', 'fa-delicious', 'asset', 'http://oa.hello.com/asset', 1, 1, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0102', '01', '客户管理', 'Customer', 'fa-delicious', 'customer', 'http://oa.hello.com/customer', 1, 2, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0103', '01', '合同管理', 'Contract', 'fa-delicious', 'contract', 'http://oa.hello.com/contract', 1, 3, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0104', '01', '项目管理', 'Project', 'fa-delicious', 'project', 'http://oa.hello.com/project', 1, 4, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0105', '01', '文档管理', 'Document', 'fa-delicious', 'document', 'http://oa.hello.com/document', 1, 5, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0106', '01', '事故管理', 'Accident', 'fa-delicious', 'accident', 'http://oa.hello.com/accident', 1, 6, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0107', '01', '消息管理', 'Message', 'fa-delicious', 'message', 'http://oa.hello.com/message', 1, 7, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0108', '01', '角色权限', 'Permission', 'fa-delicious', 'permission', 'http://oa.hello.com/permission', 1, 8, now());

-- CRM
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0201', '02', '公司', 'Company', 'fa-delicious', 'company', 'http://crm.hello.com/company', 1, 1, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0202', '02', '联系人', 'Contact', 'fa-ellipsis-h', 'contact', 'http://crm.hello.com/contact', 1, 2, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0203', '02', '合同', 'Contract', 'fa-eject', 'contract', 'http://crm.hello.com/contract', 1, 3, now());

-- 财税管理
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0301', '03', '套账管理', 'Account', 'fa-delicious', 'taozhang', 'http://account.hello.com/taozhang', 1, 1, now());

-- CMS
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0401', '04', '站点管理', 'Site Management', 'fa-sitemap', 'site', 'http://cms.hello.com/site-admin/site', 1, 1, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0402', '04', '栏目管理', 'Navigation', 'fa-navicon', 'nav', 'http://cms.hello.com/site-admin/nav', 1, 2, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0403', '04', '文章管理', 'Article', 'fa-file-text', 'article', 'http://cms.hello.com/site-admin/article', 1, 3, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0404', '04', '友情链接', 'Freid Link', 'fa-anchor', 'friendlink', 'http://cms.hello.com/site-admin/friendlink', 1, 4, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0405', '04', '轮播图', 'Swiper', 'fa-sliders', 'swiper', 'http://cms.hello.com/site-admin/swiper', 1, 5, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0406', '04', '标签管理', 'Tag', 'fa-tags', 'tag', 'http://cms.hello.com/site-admin/tag', 1, 6, now());

-- 活动
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0501', '05', '报名', 'Registe', 'fa-delicious', 'Registe', 'http://campaign.hello.com/registe', 1, 1, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0502', '05', '投票', 'Poll', 'fa-ellipsis-h', 'poll', 'http://campaign.hello.com/poll', 1, 2, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0503', '05', '抽奖', 'Lottery', 'fa-eject', 'lottery', 'http://campaign.hello.com/lottery', 1, 3, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0504', '05', '小程序', 'miniprogram', 'fa-cubes', 'miniprogram', 'http://campaign.hello.com/miniprogram', 1, 4, now());

-- 用户行为追溯
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0601', '06', '站点', 'Site Manage', 'fa-envelope', 'site', 'http://traceback.hello.com/site', 1, 1, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0602', '06', '事件', 'Event Manage', 'fa-send', 'event', 'http://traceback.hello.com/event', 1, 2, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0603', '06', '落地页', 'Land Page', 'fa-trash-o', 'landpage', 'http://traceback.hello.com/landpage', 1, 3, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0604', '06', '推广链接', 'Promotion Links', 'fa-gear', 'promotion', 'http://traceback.hello.com/promotion', 1, 4, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0605', '06', '追溯日志', 'Trace Logs', 'fa-file-text-o', 'tracelog', 'http://traceback.hello.com/tracelog', 1, 5, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0606', '06', '统计报告', 'Report', 'fa-bar-chart', 'report', 'http://traceback.hello.com/report', 1, 6, now());

-- 短信
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0701', '07', '短信任务列表', 'SMS List', 'fa-envelope', 'task', 'http://sms.hello.com/task', 1, 1, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0702', '07', '发送中的短信', 'Sending SMS', 'fa-send', 'sending', 'http://sms.hello.com/sending', 1, 2, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0703', '07', '排除列表', 'Exclusions', 'fa-trash-o', 'exclude', 'http://sms.hello.com/exclude', 1, 3, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0704', '07', '配置', 'Config', 'fa-gear', 'config', 'http://sms.hello.com/config', 1, 4, now());

insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0801', '08', '邮件任务列表', 'Mail List', 'fa-envelope', 'task', 'http://edm.hello.com/task', 1, 1, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0802', '08', '发送中的邮件', 'Sending Mail', 'fa-send', 'sending', 'http://edm.hello.com/sending', 1, 2, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0803', '08', '排除列表', 'Exclusions', 'fa-trash-o', 'exclude', 'http://edm.hello.com/exclude', 1, 3, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0804', '08', '配置', 'Config', 'fa-gear', 'config', 'http://edm.hello.com/config', 1, 4, now());

-- 基础服务
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0901', '09', '短网址', 'Short URL', 'fa-eject', 'dwz', 'http://base.hello.com/dwz', 1, 1, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0902', '09', '微信授权', 'Wx Auth', 'fa-thumbs-o-up', 'wxauth', 'http://base.hello.com/wxauth', 1, 2, now());
insert into us_module (id, product_id, name, english_name, icon, code, url, status, order_no, create_time)
    values('0903', '09', '人脸识别', 'Face', 'fa-smile-o', 'face', 'http://base.hello.com/face', 1, 3, now());

insert into ml_module (id, parent_id, name, english_name, icon, code, url, status, order_no, create_time)
    values(1, null, '邮件任务列表', 'Mail List', 'fa-envelope', 'mail-list', '/mail/list.html', 1, 1, now());
insert into ml_module (id, parent_id, name, english_name, icon, code, url, status, order_no, create_time)
    values(2, null, '发送中的邮件', 'Sending Mail', 'fa-send', 'mail-send', '/mail/sending.html', 1, 2, now());
insert into ml_module (id, parent_id, name, english_name, icon, code, url, status, order_no, create_time)
    values(3, null, '排除列表', 'Exclusions', 'fa-trash-o', 'mail-trash', '/mail/exclusion.html', 1, 3, now());
insert into ml_module (id, parent_id, name, english_name, icon, code, url, status, order_no, create_time)
    values(4, null, '配置', 'Config', 'fa-gear', 'mail-config', '/mail/config.html', 1, 4, now());

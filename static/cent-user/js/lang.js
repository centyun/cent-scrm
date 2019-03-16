/**
 * 国际化js文本处理, 包括相关的属性和方法
 */
var lang = Cookies.get('cent_lang');
var global = {"defaultLanguage" : lang ? lang : "zh_CN"};
function setLanguage(lang) {
	global.defaultLanguage = lang;
	Cookies.set('cent_lang', lang);
}
function getI18n(key) {
	return user.login[global.defaultLanguage][key];
}

var user = {login:[]};
user.login['zh_CN']={
	siteNameCantEmpty: '名称不能为空',
	domainCantEmpty: '域名不能为空',
	datatableI18n: 'Chinese.json',
	tenantStatus0: '已注册',
	tenantStatus1: '已审核',
	tenantStatus2: '已认证',
	tenantStatus3: '已冻结',
	tenantStatus4: '已注销',
	userStatus0: '已注册',
	userStatus1: '已审核',
	userStatus2: '已认证',
	userStatus3: '已冻结',
	userStatus4: '已注销',
	productStatus0: '未发布',
	productStatus1: '正常',
	productStatus2: '下线停用',
	productStatus3: '将停用',
	chargeStatus1: '充值成功',
	chargeStatus2: '取消充值'
};

user.login['en_US']={
	siteNameCantEmpty: 'Site Name Cannot be Empty',
	domainCantEmpty: 'Domain Cannot be Empty',
	datatableI18n: 'English.json',
	tenantStatus0: 'Registed',
	tenantStatus1: 'Audited',
	tenantStatus2: 'Certified',
	tenantStatus3: 'Frozen',
	tenantStatus4: 'Cancelled',
	userStatus0: 'Registed',
	userStatus1: 'Audited',
	userStatus2: 'Certified',
	userStatus3: 'Frozen',
	userStatus4: 'Cancelled',
	productStatus0: 'Unpublished',
	productStatus1: 'Enabled',
	productStatus2: 'Stopped',
	productStatus3: 'to be Expired',
	chargeStatus1: 'Charged',
	chargeStatus1: 'Cancelled'
};

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
	return account.login[global.defaultLanguage][key];
}

var account = {login:[]};
account.login['zh_CN']={
	userCantEmpty: '用户名不能为空',
	passwordCantEmpty: '密码不能为空',
	codeCantEmpty: '代码不能为空',
	mainAccountCantEmpty : '主账号不能为空',
	mainAccountPwdCantEmpty : '主账号密码不能为空',
	loginError: '登录出错',
	saveError: '保存出错',
	deleteError: '删除出错',
	loginNameCantEmpty: '登录名不能为空',
	productStatus1: '正常',
	productStatus2: '下线停用',
	productStatus3: '可用，但已有新版',
	chargeStatus1: '充值成功',
	chargeStatus2: '充值已取消',
	tenantStatus0: '已注册',
	tenantStatus1: '已审核',
	tenantStatus2: '已认证',
	tenantStatus3: '已冻结',
	tenantStatus4: '已注销',
	accountStatus0: '已注册',
	accountStatus1: '已审核',
	accountStatus2: '已认证',
	accountStatus3: '已冻结',
	accountStatus4: '已注销',
	pwdCantEmpty: '密码不能为空',
	repwdCantEmpty: '确认密码不能为空',
	pwdunequal: '两次密码不一致',
	nameCantEmpty : '名称不能为空',
	versionCantEmpty : '版本不能为空',
	publishTimeCantEmpty : '上线时间不能为空',
	productManagerCantEmpty : '产品负责人不能为空',
	delete: '删除',
	datatableI18n: 'Chinese.json'
};

account.login['en_US']={
	userCantEmpty: 'User Cannot be Empty',
	passwordCantEmpty: 'Password Cannot be Empty',
	codeCantEmpty: 'Code Cannot be Empty',
	mainAccountCantEmpty : 'Main Account Cannot be Empty',
	mainAccountPwdCantEmpty : 'Main Account Password Cannot be Empty',
	loginError: 'Login Error',
	saveError: 'Save Error',
	deleteError: 'Delete Error',
	loginNameCantEmpty: 'Login Name Cannot be Empty',
	productStatus1: 'Normal',
	productStatus2: 'Stop',
	productStatus3: 'Deprecated',
	chargeStatus1: 'Charge Success',
	chargeStatus2: 'Charge Canceled',
	tenantStatus0: 'Registed',
	tenantStatus1: 'Audited',
	tenantStatus2: 'Certified',
	tenantStatus3: 'Locked',
	tenantStatus4: 'Canceled',
	accountStatus0: 'Registed',
	accountStatus1: 'Audited',
	accountStatus2: 'Certified',
	accountStatus3: 'Locked',
	accountStatus4: 'Canceled',
	pwdCantEmpty: 'Password Required',
	repwdCantEmpty: 'Confirm Password Required',
	pwdunequal: 'Two Password Unequal',
	nameCantEmpty : 'Name Cannot be Empty',
	versionCantEmpty : 'Version Cannot be Empty',
	publishTimeCantEmpty : 'Publish Time Cannot be Empty',
	productManagerCantEmpty : 'Product Manager Cannot be Empty',
	delete: 'Delete',
	datatableI18n: 'English.json'
};

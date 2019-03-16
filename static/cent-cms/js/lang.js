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
	return cms.lang[global.defaultLanguage][key];
}

var cms = {lang:[]};
cms.lang['zh_CN']={
	languagezh_CN : '中文',
	languageen_US : '英文',
	delete: '删除',
	datatableI18n: 'Chinese.json'
};

cms.lang['en_US']={
	languagezh_CN : 'Chinese',
	languageen_US : 'English',
	delete: 'Delete',
	datatableI18n: 'English.json'
};

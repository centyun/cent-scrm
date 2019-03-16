function saveCountryRegion(saveType) {
	var arr = {};
	arr.name = $('#name').val();
	arr.englishName = $('#englishName').val();
	arr.code = $('#code').val();
	arr.continentId = $('#continentId').val();
	arr.pinyin = $('#pinyin').val();
	arr.pinyinShort = $('#pinyinShort').val();
	if(saveType == 2) { // 如果是编辑则需要给id赋值
		arr.id = $('#id').val();
	}
	$.ajax({
		url : '/location/save-country-region',
		type : 'post',
		dataType : 'json',
		data : arr,
		success : function(data) {
			if (data.status == 200) {
				window.location.href = saveType == 1 ? "/location/country-region/add.html?continentId="+arr.continentId : "/location/country-region?continentId="+arr.continentId;
			} else {
				$('#errMsg').html(data.data);
			}
		},
		error : function(data) {
			console.log(data);
			$('#errMsg').text(getI18n('saveError'));
		}
	});
}

function gotopage(page) {
	window.location.href = page;
}

function editCountryRegion() {
	window.location.href="/location/country-region/edit.html?id="+$('#id').val();
}

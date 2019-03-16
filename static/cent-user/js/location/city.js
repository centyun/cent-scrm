function saveCity(saveType) {
	var arr = {};
	arr.name = $('#name').val();
	arr.englishName = $('#englishName').val();
	arr.code = $('#code').val();
	arr.provinceId = $('#provinceId').val();
	arr.pinyin = $('#pinyin').val();
	arr.pinyinShort = $('#pinyinShort').val();
	if(saveType == 2) { // 如果是编辑则需要给id赋值
		arr.id = $('#id').val();
	}
	$.ajax({
		url : '/location/save-city',
		type : 'post',
		dataType : 'json',
		data : arr,
		success : function(data) {
			if (data.status == 200) {
				window.location.href = saveType == 1 ? "/location/city/add.html?provinceId="+arr.provinceId : "/location/city?provinceId="+arr.provinceId;
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

function editCity() {
	window.location.href="/location/city/edit.html?id="+$('#id').val();
}

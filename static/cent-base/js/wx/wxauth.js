function saveWxAuth() {
	var arr = {};
	arr.id = $('#id').val();
	arr.appId = $('#appId').val();
	arr.appSecret = $('#appSecret').val();
	$.ajax({
		url : '/wxauth/save-wxauth',
		type : 'post',
		dataType : 'json',
		data : arr,
		success : function(data) {
			if (data.status == 200) {
				var id = data.data;
				console.log(id);
				$('#id').val(id);
				$("#errMsg").show().html("保存成功").fadeOut(2000);
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

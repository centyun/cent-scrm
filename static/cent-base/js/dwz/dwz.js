function createDwz() {
	var arr = {};
	arr.longUrl = $('#longUrl').val();
	$.ajax({
		url : '/dwz/save-dwz',
		type : 'post',
		dataType : 'json',
		data : arr,
		success : function(data) {
			if (data.status == 200) {
				var dwz = data.data;
				console.log(dwz.qrcode);
				console.log(dwz.shortUrl);
				$("#qrimage").attr("src", dwz.qrcode);
				$('#shortUrl').text(dwz.shortUrl);
				$('#right').show();
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

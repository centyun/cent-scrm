function saveCharge(saveType) {
	if (checkForm()) {
		var arr = {};
		arr.tenantId = $('#tenantId').val();
		arr.productId = $('#productId').val();
		arr.money = $('#money').val();
		arr.quota = $('#quota').val();
		arr.expiredTime = $('#expiredTime').val();
		arr.note = $('#note').val();
		if(saveType == 2) { // 如果是编辑则需要给id赋值
			arr.id = $('#id').val();
		}
		$.ajax({
			url : '/charge/save-charge',
			type : 'post',
			dataType : 'json',
			data : arr,
			success : function(data) {
				if (data.status == 200) {
					window.location.href = saveType == 1 ? "/charge/add.html" : "/charge/index.html";
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
}

function checkForm() {
	var money = $('#money').val();
	if (null == money || '' == money) {
		$('#errMsg').html(getI18n('userCantEmpty'));
		return false;
	}

	var quota = $('#quota').val();
	if (null == quota || '' == quota) {
		$('#errMsg').html(getI18n('codeCantEmpty'));
		return false;
	}
	return true;
}

function gotopage(page) {
	window.location.href = page;
}

function editCharge() {
	window.location.href="/charge/edit.html?id="+$('#id').val();
}

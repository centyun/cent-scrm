function saveProduct(saveType) {
	if (checkForm()) {
		var arr = {};
		arr.name = $('#name').val();
		arr.code = $('#code').val();
		arr.version = $('#version').val();
		arr.releaseUrl = $('#releaseUrl').val();
		arr.releaseTime = $('#releaseTime').val();
		arr.productManager = $('#productManager').val();
		arr.note = $('#note').val();
		if(saveType == 2) { // 如果是编辑则需要给id赋值
			arr.id = $('#id').val();
		}
		$.ajax({
			url : '/product/save-product',
			type : 'post',
			dataType : 'json',
			data : arr,
			success : function(data) {
				if (data.status == 200) {
					window.location.href = saveType == 1 ? "/product/add.html" : "/product/index.html";
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
	var username = $('#name').val();
	if (null == username || '' == username) {
		$('#errMsg').html(getI18n('nameCantEmpty'));
		$('#name').focus();
		return false;
	}
	var code = $('#code').val();
	if (null == code || '' == code) {
		$('#errMsg').html(getI18n('codeCantEmpty'));
		$('#code').focus();
		return false;
	}
	var version = $('#version').val();
	if (null == version || '' == version) {
		$('#errMsg').html(getI18n('versionCantEmpty'));
		$('#version').focus();
		return false;
	}
	var releaseTime = $('#releaseTime').val();
	if (null == releaseTime || '' == releaseTime) {
		$('#errMsg').html(getI18n('releaseTimeCantEmpty'));
		$('#releaseTime').focus();
		return false;
	}
	var productManager = $('#productManager').val();
	if (null == productManager || '' == productManager) {
		$('#errMsg').html(getI18n('productManagerCantEmpty'));
		$('#productManager').focus();
		return false;
	}
	return true;
}

function gotopage(page) {
	window.location.href = page;
}

function editProduct() {
	window.location.href="/product/edit.html?id="+$('#id').val();
}

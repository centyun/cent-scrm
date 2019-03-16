function saveSite(saveType) {
	if (checkForm()) {
		var arr = {};
		arr.name = $('#name').val();
		arr.language = $('#language').val();
		arr.domain = $('#domain').val();
		arr.otherDomain = $('#otherDomain').val();
		arr.template = $('#template').val();
		arr.mobileTemplate = $('#mobileTemplate').val();
		arr.status = $("#status").get(0).checked ? 1 : 0;
		if(saveType == 2) { // 如果是编辑则需要给id赋值
			arr.id = $('#id').val();
		}
		console.log(arr);
		$.ajax({
			url : '/site-admin/site/save-site',
			type : 'post',
			dataType : 'json',
			data : arr,
			success : function(data) {
				console.log(data.status);
				if (data.status == 200) {
					window.location.href = saveType == 1 ? "/site-admin/site/add.html" : "/site-admin/site/index.html";
				} else if (data.status == 406) {
					var errors = data.data;
					var error = "";
					for (var i = 0; i < errors.length; i++) {
						if(i > 0) {
							error += "<br/>";
						}
						error += errors[i];
					}
					$('#errMsg').html(error);
				} else {
					console.log(data.data);
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
	var name = $('#name').val();
	if (null == name || '' == name) {
		$('#errMsg').html(getI18n('siteNameCantEmpty'));
		$('#name').focus();
		return false;
	}

	var domain = $('#domain').val();
	if (null == domain || '' == domain) {
		$('#errMsg').html(getI18n('domainCantEmpty'));
		$('#domain').focus();
		return false;
	}
	return true;
}

function gotopage(page) {
	window.location.href = page;
}

function editSite() {
	window.location.href="/site-admin/site/edit.html?id="+$('#id').val();
}

function editSiteAttribute() {
	window.location.href="/site-admin/site/attributeEdit?id="+$('#id').val();
}

function toSiteAttribute(){
	window.location.href='/site-admin/site/attribute?id='+$('#id').val();
}

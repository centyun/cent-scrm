function saveTenant(saveType) {
	if (checkForm()) {
		var arr = {};
		arr.name = $('#name').val();
		arr.code = $('#code').val();
		arr.contact = $('#contact').val();
		arr.mobile = $('#mobile').val();
		arr.phone = $('#phone').val();
		arr.email = $('#email').val();
		arr.address = $('#address').val();
		arr.logo = $('#logo').val();
		arr.type = $('#type').val();
		arr.note = $('#note').val();
		if(saveType == 2) { // 如果是编辑则需要给id赋值
			arr.id = $('#id').val();
		} else {
			arr.mainUser = $('#mainUser').val(); // 新增必须有主账号
			if (null == arr.mainUser || '' == arr.mainUser) {
				$('#errMsg').html(getI18n('mainUserCantEmpty'));
				$('#mainUser').focus();
				return false;
			}
			var mainUserPwd = $('#mainUserPwd').val(); // 新增必须有主账号密码
			if (null == mainUserPwd || '' == mainUserPwd) {
				$('#errMsg').html(getI18n('mainUserPwdCantEmpty'));
				$('#mainUserPwd').focus();
				return false;
			} else {
				arr.mainUserPwd = sha256(mainUserPwd);
			}
		}
		$.ajax({
			url : '/tenant/save-tenant',
			type : 'post',
			dataType : 'json',
			data : arr,
			success : function(data) {
				console.log(data.status);
				if (data.status == 200) {
					window.location.href = saveType == 1 ? "/tenant/add.html" : "/tenant/index.html";
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
	return true;
}

function uploadLogo() {
	$.ajaxFileUpload({
		url : "/upload-file",
		type : 'post',
		secureuri : false,
		fileElementId : 'file',
		dataType : 'text',
		success : function(data) {
			data = data.replace(/<.*?>/ig, "")
			data = $.parseJSON(data);
			if (data.status == 200) {
				$("#logo").val(data.data);
				$("#img").html("<img src='"+data.data+"'> <a href='#' onclick='deleteUploadImage()'>" + getI18n('delete') + "</a>");
			}else{
				$("#img").html("");
			}
		},
		error : function(error) {
			console.log(error);
			alert("上传Fail");
		}
	});
}

/**
 * 删除图片
 */
function deleteUploadImage(){
	if(confirm("确认要删除Logo？")){
		$("#logo").val("");
		$("#img").html("");
	}
}

function gotopage(page) {
	window.location.href = page;
}

function editTenant() {
	window.location.href="/tenant/edit.html?id="+$('#id').val();
}
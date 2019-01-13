function saveUser(saveType) {
	var arr = {};
	arr.loginName = $('#loginName').val();
	arr.type = $('#type').val();
	arr.mobile = $('#mobile').val();
	arr.displayName = $('#displayName').val();
	arr.realName = $('#realName').val();
	arr.tenantId = $('#tenantId').val();
	arr.phone = $('#phone').val();
	arr.email = $('#email').val();
	arr.headImage = $('#headImage').val();
	arr.gender = $('#gender').val();
	if(saveType == 2) { // 如果是编辑则需要给id赋值
		arr.id = $('#id').val();
	} else {
		if (null == arr.loginName || '' == arr.loginName) {
			$('#errMsg').html(getI18n('loginNameCantEmpty'));
			return false;
		}
		var password = $('#password').val();
		var repassword = $('#repassword').val();
		if (null == password || '' == password) {
			$('#errMsg').html(getI18n('pwdCantEmpty'));
			return false;
		}
		if (null == repassword || '' == repassword) {
			$('#errMsg').html(getI18n('repwdCantEmpty'));
			return false;
		}
		if(password !== repassword) {
			$('#errMsg').html(getI18n('pwdunequal'));
			return false;
		}
		arr.password = sha256(password);
	}
	$.ajax({
		url : '/user/save-user',
		type : 'post',
		dataType : 'json',
		data : arr,
		success : function(data) {
			if (data.status == 200) {
				window.location.href = saveType == 1 ? "/user/add.html" : "/user/index.html";
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

function uploadHead() {
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
				$("#headImage").val(data.data);
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
	if(confirm("确认要删除头像？")){
		$("#headImage").val("");
		$("#img").html("");
	}
}

function gotopage(page) {
	window.location.href = page;
}

function editUser() {
	window.location.href="/user/edit.html?id="+$('#id').val();
}
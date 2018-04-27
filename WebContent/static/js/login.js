$(function(){
	$('#login').dialog({
		title : '系统登录',
		width : 300,
		height : 180,
		modal : true,
		iconCls : 'icon-login',
		buttons : '#btn',
	});
	
	$('#usercode').validatebox({
		required : true,
		missingMessage : '请输入账号',
		
	});
	$('#password').validatebox({
		required : true,
		missingMessage : '请输入密码',
	});
	
	//加载时，光标聚焦
	if(!$('#usercode').validatebox('isValid')){
		$('#usercode').focus(); 
	}else if(!$('#password').validatebox('isValid')){
		$('#password').focus();
	}
	//点击登录
	$('#btn a').click(function(){
		if(!$('#usercode').validatebox('isValid')){
			$('#usercode').focus(); 
		}else if(!$('#password').validatebox('isValid')){
			$('#password').focus();
		}else{
			$.ajax({
				url : $('#rooturl').val() + '/checkLogin',
				type : 'post',
				data : {
					usercode : $('#usercode').val(),
					password : $('#password').val()
				},
				beforeSend : function(){
					$.messager.progress({
						text : '正在登陆中......'
					});
				},
				success : function(data,response,status){
					$.messager.progress('close');
					if(data == 'success'){
						location.href = $('#rooturl').val() + '/manager';
					}else{
						$.messager.alert('登录失败！','用户名或密码不正确！','warning',function(){
							$('#password').select();
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$.messager.alert('系统错误','服务器连接错误(' + textStatus + ')，请稍后重试!','error')
				}
			});
		}
	});
	
	
});
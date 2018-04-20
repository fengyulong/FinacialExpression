$(function(){
	$('#login').dialog({
		title : '系统登录',
		width : 300,
		height : 180,
		modal : true,
		iconCls : 'icon-login',
		buttons : '#btn',
	});
	
	$('#username').validatebox({
		required : true,
		missingMessage : '请输入账号',
		
	});
	$('#password').validatebox({
		required : true,
		missingMessage : '请输入密码',
	});
	
	//加载时，光标聚焦
	if(!$('#username').validatebox('isValid')){
		$('#username').focus(); 
	}else if(!$('#password').validatebox('isValid')){
		$('#password').focus();
	}
	//点击登录
	$('#btn a').click(function(){
		if(!$('#username').validatebox('isValid')){
			$('#username').focus(); 
		}else if(!$('#password').validatebox('isValid')){
			$('#password').focus();
		}
	});
	
	
});
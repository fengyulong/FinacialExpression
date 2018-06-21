$(function() {
	$('#login').dialog({
		title : '系统登录',
		width : 380,
		height : 260,
		modal : true,
		iconCls : 'icon-login',
		closable : false,
	});
	$("input[name='username']").validatebox({
		required : true,
	});

	$('.easyui-linkbutton').click(function() {
		if(!$("input[name='username']").validatebox('isValid')){
			$.messager.show({
				title : "登录失败" ,
				msg : '用户名密码不正确',
				showType : 'slide',
			});
			return ;
		}
		$('form').submit();
		
	});
	
	

});
 
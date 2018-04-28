<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Form - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
	
</head>
<body>
	<div id="login" style="padding:20px;">
		<form action="${path}/login" method="post">
			<div style="margin-bottom:10px">
				<input name="username" class="easyui-textbox " style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入用户名',iconCls:'icon-man',iconWidth:38" />
			</div>
			<div style="margin-bottom:20px">
				<input name="password" class="easyui-textbox " type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入密码',iconCls:'icon-lock',iconWidth:38" />
			</div>
			<div style="margin-bottom:20px">
				<input name="rememberMe" type="checkbox" checked="checked">
				<span> 记住我 </span>
			</div>
			<div id="btn">
				<a href="#" class="easyui-linkbutton" style="padding:5px 5px;width:100%;">
					<span style="font-size:14px;">登录</span>
				</a>
			</div>
		</form>
	</div>	
	
<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/static/js/login.js"></script>
<script type="text/javascript">
<#if msg?exists>
  	$.messager.show({
				title : "登录失败" ,
				msg : '${msg}',
				showType : 'slide',
			});
</#if>  

</script>
</body>
</html>
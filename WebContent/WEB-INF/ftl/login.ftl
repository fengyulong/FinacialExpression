<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Form - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/login.css">
	
</head>
<body>
	<div id="login">
		<p>账号　<input type="text" id="usercode" class="textbox"/></p>
		<p>密码　<input type="text" id="password" class="textbox"/></p>
		<input id="rooturl" type="hidden" value="${path}"/>
	</div>
	<div id="btn">
		<a href="#" class="easyui-linkbutton">登录</a>
	</div>
	
	
<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/static/js/login.js"></script>
</body>
</html>
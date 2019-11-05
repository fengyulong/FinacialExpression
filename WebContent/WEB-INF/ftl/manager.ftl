<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>管理页面</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
</head>
<body>
	<div class="easyui-layout">
		<div data-options="region:'north'" style="height:50px">
			<@shiro.principal property="username"/><a href="${path}/logout">退出</a>
		</div>
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		<div data-options="region:'west',split:true" title="导航" style="width:200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<#list menuList as accordion>
				<@shiro.hasPermission name = accordion.code>
				<div title="${accordion.name}" data-options="iconCls:'${accordion.icon}'">
					<#list accordion.children as menu>
					<@shiro.hasPermission name = menu.code>
					<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'${menu.icon}'" url="${path + menu.url}" style="width:98%;  margin-bottom:2px; text-align:left;">${menu.name}</a>
					</@shiro.hasPermission>  
					</#list>
				</div>
				</@shiro.hasPermission>  
				</#list>
			</div>
		</div>
		<div data-options="region:'center'">
			<div class="easyui-tabs" data-options="fit:true,border:false">
			</div>
		</div>
	</div>
<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/static/js/manager.js"></script>
</body>
</html>
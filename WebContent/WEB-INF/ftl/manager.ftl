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
		<div data-options="region:'north'" style="height:50px"></div>
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		<div data-options="region:'west',split:true" title="West" style="width:200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="系统设置">
					<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" url="${path}/sys/user/list" style="width:98%;  margin-bottom:2px; text-align:left;">用户管理</a>
					<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" url="${path}/sys/dict/list" style="width:98%;  margin-bottom:2px; text-align:left;">字典管理</a>
					<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" url="${path}/sys/permission/list" style="width:98%;  margin-bottom:2px; text-align:left;">权限管理</a>
				</div>
				<div title="系统设置">
					<a href="#" id="b3" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" style="width:98%;  margin-bottom:5px; text-align:left;">菜单管理</a>
				</div>
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
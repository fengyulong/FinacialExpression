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
		<div data-options="region:'west',split:true" title="West" style="width:200px;"></div>
		<div data-options="region:'center',title:'Main Title'">
			
		</div>
	</div>
<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/static/js/manager.js"></script>
</body>
</html>
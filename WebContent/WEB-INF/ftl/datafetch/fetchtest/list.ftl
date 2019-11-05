<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>取数测试</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" style="width:200px">
			<ul id="orgmapping_tree"></ul>
		</div>
		<div data-options="region:'center'">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north'" style="height:50%">

				</div>
				<div data-options="region:'center'">

				</div>
			</div>
		</div>

		<div data-options="region:'center',split:true" >

		</div>
	</div>
	
	
			

	
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.extend.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		var orgMappingTree
		$(function() {
			orgMappingTree = $('#orgmapping_tree').tree({
				url : '${path}/datafetch/orgmapping/tree',
				onSelect : function(node){
					orgMappingGrid.datagrid('load',{code : node.id});
				}
			});

		});
	
	
	</script>
</body>

<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>权限管理</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
</head>
<body>
	<div id="tb" style="padding:5px">
		<div>
			<a href="#" class="easyui-menubutton" data-options="menu:'#create_menu',iconCls:'icon-add',plain:true" >新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()" >编辑</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
		</div>
		<div id="create_menu">
			<div onclick="add_group()">新增分组</div>
			<div onclick="add_menu()">新增菜单</div>
			<div>新增权限</div>
		</div>
	</div>
	<table id="permission_table"></table>
	<div id="dlg"></div>
	
<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/static/css/themes/icon.js"></script>
<script type="text/javascript">
	var permission_table,dlg;
	permission_table = $('#permission_table').treegrid({
		url : '${path}/sys/permission/query',
		idField:'id',
		treeField:'name',
		iconCls : 'icon',
		collapsible: true,
		fitColumns: true,
		toolbar : '#tb',
		columns:[[
					{field:'name',title:'名称',width:180,iconCls:'icon-add'},
					{field:'url',title:'资源路径',width:60},
					{field:'type',title:'资源类型',width:80},
					{field:'remark',title:'描述',width:80}
			    ]]
	});
	
	function add_group(){
		dlg = $('#dlg').dialog({
			width : 250,
			height : 240,
			href : '${path}/sys/permission/edit/GROUP',
			title: '新增菜单分组',
			modal: true,
			buttons:[{
				text:'保存',
				handler:function(){
					$('#edit_form').submit(); 
				}
			},{
				text:'取消',
				handler:function(){
						dlg.panel('close');
					}
			}]
			
		});
	}
	
	function add_menu(){
		dlg = $('#dlg').dialog({
			width : 250,
			height : 280,
			href : '${path}/sys/permission/edit/MENU',
			title: '新增菜单分组',
			modal: true,
			buttons:[{
				text:'保存',
				handler:function(){
					$('#edit_form').submit(); 
				}
			},{
				text:'取消',
				handler:function(){
						dlg.panel('close');
					}
			}]
			
		});
	}
	
	function edit(){
		var row = permission_table.treegrid('getSelected');
		if(row){
			if(row.type == 'GROUP'){
				dlg = $('#dlg').dialog({
					width : 250,
					height : 240,
					href : '${path}/sys/permission/edit/GROUP?id='+row.id,
					title: '编辑菜单分组',
					modal: true,
					buttons:[{
						text:'保存',
						handler:function(){
							$('#edit_form').submit(); 
						}
					},{
						text:'取消',
						handler:function(){
								dlg.panel('close');
							}
					}]
				});
			}else if(row.type == 'MENU'){
				dlg = $('#dlg').dialog({
					width : 250,
					height : 280,
					href : '${path}/sys/permission/edit/MENU?id='+row.id,
					title: '编辑菜单分组',
					modal: true,
					buttons:[{
						text:'保存',
						handler:function(){
							$('#edit_form').submit(); 
						}
					},{
						text:'取消',
						handler:function(){
								dlg.panel('close');
							}
					}]
				});
			}
			
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请选择要编辑的数据！" });
		}
	}
	
</script>
</body>

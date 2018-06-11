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
			<@shiro.hasPermission name = "sys:permission:add">
			<a href="#" class="easyui-menubutton" data-options="menu:'#create_menu',iconCls:'icon-add',plain:true" >新增</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name = "sys:permission:edit">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()" >编辑</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name = "sys:permission:delete">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
			</@shiro.hasPermission>
		</div>
		<@shiro.hasPermission name = "sys:permission:add">
		<div id="create_menu">
			<div onclick="add_group()">新增分组</div>
			<div onclick="add_menu()">新增菜单</div>
			<div onclick="add_button()">新增按钮</div>
		</div>
		</@shiro.hasPermission>
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
		collapsible: true,
		fitColumns: true,
		toolbar : '#tb',
		columns:[[
					{field:'name',title:'名称',width:180,iconCls:'icon-add'},
					{field:'url',title:'资源路径',width:60},
					{field:'code',title:'权限代码',width:60},
					{field:'type',title:'资源类型',width:40},
					{field:'remark',title:'描述',width:80}
			    ]]
	});
	
	
	function add_group(){
		dlg = $('#dlg').dialog({
			width : 250,
			height : 260,
			href : '${path}/sys/permission/add/GROUP',
			title: '新增菜单分组',
			modal: true,
			buttons:[{
				text:'保存',
				handler:function(){
					$('#add_group_form').submit(); 
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
			height : 320,
			href : '${path}/sys/permission/add/MENU',
			title: '新增菜单分组',
			modal: true,
			buttons:[{
				text:'保存',
				handler:function(){
					$('#add_menu_form').submit(); 
				}
			},{
				text:'取消',
				handler:function(){
						dlg.panel('close');
					}
			}]
			
		});
	}
	
	function add_button(){
		dlg = $('#dlg').dialog({
			width : 250,
			height : 280,
			href : '${path}/sys/permission/add/BUTTON',
			title: '新增菜单分组',
			modal: true,
			buttons:[{
				text:'保存',
				handler:function(){
					$('#add_button_form').submit(); 
				}
			},{
				text:'取消',
				handler:function(){
						dlg.panel('close');
					}
			}]
			
		});
	}
	
	function del(){
		var row = permission_table.treegrid('getSelected');
		if(row){
		    var confirmMessage = permission_table.treegrid('getChildren',row.id).length>0?'确定要删除【'+row.name+'】及其子节点吗？':'确定要删除【'+row.name+'】节点吗？';
		    parent.$.messager.confirm('提示',confirmMessage,function(data){
		    	if(data){
		    		$.ajax({
						type : 'post',
						url : '${path}/sys/permission/delete',
						data : {id : row.id},
						success : function(data){
							if(data == 'success'){
								permission_table.treegrid('reload');
								parent.$.messager.show({ title : "提示",msg: "操作成功！"});
							}else{
								parent.$.messager.alert(data);
							}	
						}
					});
		    	}
		    });
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请选择要删除的数据！" });
		}
	}
	
	function edit(){
		var row = permission_table.treegrid('getSelected');
		if(row){
			if(row.type == 'GROUP'){
				dlg = $('#dlg').dialog({
					width : 250,
					height : 260,
					href : '${path}/sys/permission/edit?id='+row.id,
					title: '编辑菜单分组',
					modal: true,
					buttons:[{
						text:'保存',
						handler:function(){
							$('#edit_group_form').submit(); 
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
					height : 320,
					href : '${path}/sys/permission/edit?id='+row.id,
					title: '编辑菜单',
					modal: true,
					buttons:[{
						text:'保存',
						handler:function(){
							$('#edit_menu_form').submit(); 
						}
					},{
						text:'取消',
						handler:function(){
								dlg.panel('close');
							}
					}]
				});
			}else if(row.type == 'BUTTON'){
				dlg = $('#dlg').dialog({
					width : 250,
					height : 280,
					href : '${path}/sys/permission/edit?id='+row.id,
					title: '编辑按钮',
					modal: true,
					buttons:[{
						text:'保存',
						handler:function(){
							$('#edit_button_form').submit(); 
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

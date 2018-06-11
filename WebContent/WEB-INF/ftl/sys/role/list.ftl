<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色列表</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
</head>
<body>
	<table id="role_table"></table>
	<div id="dlg"></div>
	<div id="tb" style="padding:5px">
		<div>
			<@shiro.hasPermission name = "sys:role:add">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name = "sys:role:edit">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()" >编辑</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name = "sys:role:delete">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name = "sys:role:permission">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-permission',plain:true" onclick="permission()">权限</a>
			</@shiro.hasPermission>
		</div>
	</div>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	var role_table,dlg,permissionTree;
	$(function() {
		role_table = $('#role_table').datagrid({
			url : '${path}/sys/role/query',
			pagination : true,
			singleSelect:true,
			pageSize : 5,
			pageList : [ 5, 10 ],
			rownumbers : true,
			striped : true,
			columns : [ [ 
			{field : 'id',hidden : true,},
			{field : 'name',title : '角色名称',width : 100,sortable : true,},
			{field : 'code',title : '角色代码',width : 100,sortable : true,}, 
			{field : 'remark',title : '备注',width : 100,sortable : false,},
			{field : 'sort',title : '排序',width : 100,sortable : true,}
			] ],
			toolbar : '#tb'
		});
	});
	
	function add(){
		dlg = $('#dlg').dialog({
			width : 260,
			height : 250,
			href : '${path}/sys/role/add',
			title: '新增角色',
			modal: true,
			buttons:[{
				text:'保存',
				handler:function(){
					$('#add_form').submit(); 
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
		var row = role_table.datagrid('getSelected');
		if(row){
			dlg = $('#dlg').dialog({
				width : 260,
				height : 250,
				href : '${path}/sys/role/edit?id='+row.id,
				title: '修改角色',
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
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请选择要编辑的数据！" });
		}
	}
	
	function del(){
		var row = role_table.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm('提示','删除后无法回复，您确定要删除？',function(data){
				if(data){
					$.ajax({
						type : 'post',
						url : '${path}/sys/role/delete',
						data : row,
						success : function(data){
							if(data == 'success'){
								role_table.datagrid('reload');
								parent.$.messager.show({ title : "提示",msg: "操作成功！"});
							}else{
								parent.$.messager.alert(data);
							}	
						}
					});
				}
			})
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请选择要删除的数据！" });
		}
	}
	
	function permission(){
		var row = role_table.datagrid('getSelected');
		if(row){
			dlg = $('#dlg').dialog({
				width : 260,
				height : 250,
				href : '${path}/sys/role/permission?roleId='+row.id,
				title: '设置权限',
				modal: true,
				buttons:[{
					text:'保存',
					handler:function(){
						submitPermission(permissionTree,row.id);
					}
				},{
					text:'取消',
					handler:function(){
							dlg.panel('close');
						}
				}]
			});
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请选择要编辑的数据！" });
		}
	}
	
	function submitPermission(pTree,roleId){
		var nodes = pTree.tree('getChecked');
		var permissionIds = '';
		for(var i=0;i<nodes.length;i++){
			if(i == 0){
				permissionIds += nodes[i].id;
			}else{
				permissionIds += ','+nodes[i].id
			}
		}
		$.ajax({
			type : 'post',
			url : '${path}/sys/role/permission',
			data : {"list" : permissionIds,"roleId" : roleId},
			success :  function(data){
				if(data == 'success'){
					parent.$.messager.show({ title : "提示",msg: "操作成功！"});
					dlg.panel('close');
				}else{
					parent.$.messager.alert(data);
				}	
			}
		});
	}
	
	
	</script>
</body>

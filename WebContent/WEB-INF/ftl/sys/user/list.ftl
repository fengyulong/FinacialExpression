<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
</head>
<body>
	<table id="user_table"></table>
	<div id="dlg"></div>
	<div id="tb" style="padding:5px">
		<div>
			<@shiro.hasPermission name = "sys:user:add">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name = "sys:user:edit">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()" >编辑</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name = "sys:user:delete">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name = "sys:user:role">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-role',plain:true" onclick="role()">角色</a>
			</@shiro.hasPermission>
		</div>
		<div>
			<form id="searchFrom">
				<input type="text" class="easyui-textbox" name="username" data-options="prompt:'用户名'"/>
				<input type="text" class="easyui-textbox" name="mobile" data-options="prompt:'手机号'"/>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="query()">查询</a>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	var d,dg,role_table;
	
	
	$(function() {
		dg = $('#user_table').datagrid({
			url : '/expression/sys/user/query',
			pagination : true,
			singleSelect:true,
			pageSize : 10,
			pageList : [ 10, 20 , 30],
			rownumbers : true,
			striped : true,
			columns : [ [ 
			{field : 'id',hidden : true,},
			{field : 'username',title : ' 用户名',width : 100,sortable : true,},
			{field : 'nickname',title : ' 昵称',width : 100,sortable : true,}, 
			{field : 'status',title : '状态',width : 100,sortable : true,formatter : function(value,row,index){
				if(value == 'E'){
					return '正常';
				}else if(value == 'D'){
					return '禁用';
				}else if(value == 'L'){
					return '锁定';
				}
			}},
			{field : 'email',title : ' 邮箱',width : 100,sortable : true,},
			{field : 'mobile',title : ' 手机号',width : 100,sortable : true,},
			{field : 'sex',title : ' 性别',width : 100,sortable : true,formatter : function(value,row,index){
				if(value == 'M'){
					return '男';
				}else if(value == 'F'){
					return '女';
				}
			}} 
			] ],
			toolbar : '#tb'
		});
	
	});
	
	function add() {
		d = $('#dlg').dialog({
			width : 500,
			height : 300,
			href : '${path}/sys/user/add',
			title: '新增用户',
			modal: true,
			buttons:[{
				text:'保存',
				handler:function(){
					$('#add_form').submit(); 
				}
			},{
				text:'取消',
				handler:function(){
						d.panel('close');
					}
			}]
		});
	}
	
	function edit(){
		var row = dg.datagrid('getSelected');
		if(row){
			d = $('#dlg').dialog({
				width : 500,
				height : 300,
				href : '${path}/sys/user/edit?id='+row.id,
				title: '修改用户',
				modal: true,
				buttons:[{
					text:'保存',
					handler:function(){
						$('#edit_form').submit(); 
					}
				},{
					text:'取消',
					handler:function(){
							d.panel('close');
						}
				}]
			});
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请选要编辑的数据！" });
		}
	}
	
	function del(){
		var row = dg.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm('提示','删除后无法回复，您确定要删除？',function(data){
				if(data){
					$.ajax({
						type : 'post',
						url : '${path}/sys/user/delete',
						data : row,
						success : function(data){
							if(data == 'success'){
								dg.datagrid('reload');
								parent.$.messager.show({ title : "提示",msg: "操作成功！"});
							}else{
								parent.$.messager.alert(data);
							}	
						}
					});
				}
			})
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请选要删除的数据！" });
		}
	}
	function query(){
		var obj= {};
		 $.each($("#searchFrom").serializeArray(),function(index){
		 	if(obj[this['name'] ]){
               obj[this['name'] ] = obj[this['name'] ] + "," + this['value'];
           	}else{
              obj[this['name'] ]=this['value'];
           	}
		 });
		dg.datagrid('load',obj);
	}
	
	function role(){
		var row = dg.datagrid('getSelected');
		if(row){
			d = $('#dlg').dialog({
				width : 500,
				height : 260,
				href : '${path}/sys/user/role?userId='+row.id,
				title: '设置角色',
				modal: true,
				buttons:[{
					text:'保存',
					handler:function(){
						submitRole(role_table,row.id);						
					}
				},{
					text:'取消',
					handler:function(){
							d.panel('close');
						}
				}]
			});
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请选择要设置角色的数据！" });
		}
	}
	
	function submitRole(roleTable,userId){
		var roles = roleTable.datagrid('getChecked');
		var roleIds = '';
		for(var i=0;i<roles.length;i++){
			if(i == 0){
				roleIds += roles[i].id;
			}else{
				roleIds += ',' + roles[i].id;
			}
		}
		$.ajax({
			type : 'post',
			url : '${path}/sys/user/role',
			data : {"list" : roleIds,"userId" : userId},
			success :  function(data){
				if(data == 'success'){
					parent.$.messager.show({ title : "提示",msg: "操作成功！"});
					d.panel('close');
				}else{
					parent.$.messager.alert(data);
				}	
			}
		});
	}
	</script>
</body>

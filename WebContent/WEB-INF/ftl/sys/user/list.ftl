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
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()" >编辑</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
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
	var d,dg;
	$(function() {
		dg = $('#user_table').datagrid({
			url : '/expression/sys/user/query',
			pagination : true,
			singleSelect:true,
			pageSize : 5,
			pageList : [ 5, 10 ],
			rownumbers : true,
			striped : true,
			columns : [ [ 
			{field : 'id',hidden : true,},
			{field : 'username',title : ' 用户名',width : 100,sortable : true,},
			{field : 'nickname',title : ' 昵称',width : 100,sortable : true,}, 
			{field : 'password',title : ' 密码',width : 100,sortable : true,},
			{field : 'email',title : ' 邮箱',width : 100,sortable : true,},
			{field : 'mobile',title : ' 手机号',width : 100,sortable : true,},
			{field : 'sex',title : ' 性别',width : 100,sortable : true,formatter : function(value,row,index){if(value == 'M'){ return '男';}else if(value == 'F') {return '女';}else{ return '';}}} 
			] ],
			toolbar : '#tb'
		});
	
	});
	
	function add() {
		d = $('#dlg').dialog({
			width : 500,
			height : 300,
			href : '${path}/sys/user/edit',
			title: '新增用户',
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
			parent.$.messager.show({ title : "提示",msg: "请选择行数据！" });
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
			parent.$.messager.show({ title : "提示",msg: "请选择行数据！" });
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
	</script>
</body>

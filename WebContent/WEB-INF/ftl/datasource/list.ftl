<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>数据源管理</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north'" style="height:50%">
			<table id="datasource_table"></table>
			<div id="tb" style="padding:5px">
				<div>
					<@shiro.hasPermission name = "datasource:add">
					<a href="#" id="datasource_add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
					</@shiro.hasPermission>
					<@shiro.hasPermission name = "datasource:edit">
					<a href="#" id="datasource_edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()" >编辑</a>
					</@shiro.hasPermission>
					<@shiro.hasPermission name = "datasource:add">
					<a href="#" id="datasource_save" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="save()" >保存</a>
					</@shiro.hasPermission>
					<@shiro.lacksPermission name = "datasource:add">
					<@shiro.hasPermission name = "datasource:edit">
					<a href="#" id="datasource_save" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="save()" >保存</a>
					</@shiro.hasPermission>
					</@shiro.lacksPermission>
					<@shiro.hasPermission name = "datasource:delete">
					<a href="#" id="datasource_delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
					</@shiro.hasPermission>
				</div>
			</div>
		</div>
		<div data-options="region:'center'">
			<form id="datasource_form" action="${path}/datasource/add" method="post">
				<table style="padding:10px;width:500px;">
			    	<tr>
						<td>名称:</td>
						<td>
							<input class="easyui-textbox" type="text" name="name" data-options="required:true" value="" />
						</td>
						<td>代码:</td>
						<td>
							<input class="easyui-textbox" type="text" name="code" data-options="required:true" value="" />
						</td>
					</tr>
					<tr>
						<td>版本:</td>
						<td>
							<input class="easyui-combobox" name="softVersion"  data-options="valueField:'value',textField:'text',editable:false,data:[{value:'EBS',text:'EBS'}]" />
						</td>
						<td>数据库:</td>
						<td>
							<input class="easyui-combobox" name="dbType"  data-options="valueField:'value',textField:'text',editable:false,data:[{value:'MySql',text:'MySql'},{value:'SqlServer',text:'SqlServer'},{value:'Oracle',text:'Oracle'}]" />
						</td>
					</tr>
					<tr>
						<td>账号:</td>
						<td>
							<input class="easyui-textbox" type="text" name="userName" data-options="required:true" value="" />
						</td>
						<td>密码:</td>
						<td>
							<input class="easyui-textbox" type="password" name="userPassword" data-options="required:true" value="" />
						</td>
					</tr>
					<tr>
						<td>主机:</td>
						<td>
							<input class="easyui-textbox" type="text" name="host" data-options="required:true" value="" />
						</td>
						<td>端口:</td>
						<td>
							<input class="easyui-textbox" type="text" name="port" data-options="required:true" value="" />
						</td>
					</tr>
					<tr>
						<td>实例:</td>
						<td>
							<input class="easyui-textbox" type="text" name="instance" data-options="required:true" value="" />
						</td>
						<td></td>
						<td>
							<a href="#" class="easyui-linkbutton" onclick="test()">测试连接</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	var datasource_table,datasource_form,form_state = '';
	$(function(){
		datasource_table = $('#datasource_table').datagrid({
			url : '${path}/datasource/list',
			//pagination : true,
			singleSelect:true,
			//pageSize : 5,
			//pageList : [ 5, 10 , 15],
			rownumbers : true,
			striped : true,
			idField : 'code',
			columns : [ [ 
			{field : 'name',title : '名称',width : 100,sortable : true,},
			{field : 'code',title : '代码',width : 100,sortable : true,}, 
			{field : 'softVersion',title : '核算软件版本',width : 100,sortable : true,},
			{field : 'dbType',title : '数据库版本',width : 100,sortable : true,},
			{field : 'instance',title : '实例名',width : 100,sortable : true,},
			{field : 'host',title : '主机',width : 100,sortable : true,},
			{field : 'port',title : '端口号',width : 100,sortable : true,} ,
			{field : 'userName',title : '登录用户',width : 100,sortable : true,},
			{field : 'userPassword',title : '登录密码',width : 100,sortable : true,}  
			] ],
			toolbar : '#tb',
			
			onSelect : function(rowIndex, rowData){
				disableForm();
				fillForm(rowData);
				setToolBar(true,true,false,true);
			},
		});
		disableForm();
		
	});
	
	function add(){
		enableForm();
		clearForm();
		setToolBar(false,false,true,false);
		$('#datasource_form').form({
			url : '${path}/datasource/add',
			success : function(data){
				if(data == 'success'){
					parent.$.messager.show({ title : "提示",msg: "新增成功！"});
					var row = getFormData();
					datasource_table.datagrid('appendRow',row);
					datasource_table.datagrid('selectRecord',row.code);
				}else{
					parent.$.messager.alert(data);
				}	
			}
		});
	}
	
	function edit(){
		enableForm();
		setToolBar(false,false,true,false);
		$('#datasource_form').form({
			url : '${path}/datasource/edit',
			success : function(data){
				if(data == 'success'){
					parent.$.messager.show({ title : "提示",msg: "修改成功！"});
					var fRow = getFormData();
					var rowIndex = datasource_table.datagrid('getRowIndex',fRow.code);
					datasource_table.datagrid('updateRow',{index : rowIndex,row : fRow});
					datasource_table.datagrid('selectRecord',fRow.code);
					
				}else{
					parent.$.messager.alert(data);
				}	
			}
		});
	}
	
	function delete(){
		var row = datasource_table.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm('提示','删除后无法回复，您确定要删除？',function(data){
				if(data){
					$.ajax({
						type : 'post',
						url : '${path}/datasource/delete',
						data : row,
						success : function(data){
							if(data == 'success'){
								var rowIndex = datasource_table.datagrid('getRowIndex',fRow.code);
								datasource_table.datagrid('deleteRow',rowIndex);
								parent.$.messager.show({ title : "提示",msg: "操作成功！"});
							}else{
								parent.$.messager.alert(data);
							}
						}
					});
				}
			
			});
		}
	}
	
	function save(){
		$('#datasource_form').submit();
	}
	
	
	function setToolBar(isAdd,isEdit,isSave,isDelete){
		if(isAdd){
			$('#datasource_add').linkbutton('enable');
		}else{
			$('#datasource_add').linkbutton('disable');
		}
		if(isEdit){
			$('#datasource_edit').linkbutton('enable');
		}else{
			$('#datasource_edit').linkbutton('disable');
		}
		if(isSave){
			$('#datasource_save').linkbutton('enable');
		}else{
			$('#datasource_save').linkbutton('disable');
		}
		if(isDelete){
			$('#datasource_delete').linkbutton('enable');
		}else{
			$('#datasource_delete').linkbutton('disable');
		}
	}
	
	
	function disableForm(){
		$("#datasource_form .easyui-textbox").textbox({disabled:true});
		$("#datasource_form .easyui-combobox").combobox({disabled:true});
	}
	
	function enableForm(){
		$("#datasource_form .easyui-textbox").textbox({disabled:false});
		$("#datasource_form .easyui-combobox").combobox('enable');
	}
	
	function clearForm(){
		$("#datasource_form .easyui-textbox").textbox('clear');
		$("#datasource_form .easyui-combobox").combobox('clear');
	}
	
	function submitForm(){
		$("#datasource_form").submit();
	}
	
	function fillForm(rowData){
		$("#datasource_form").form('load',rowData);
	}
	
	function getFormData(){
		var o = {};
		var a = $('#datasource_form').serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	}
	
	
	
	
	</script>
</body>

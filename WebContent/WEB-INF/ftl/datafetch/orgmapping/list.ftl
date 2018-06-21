<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>机构映射管理</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false">
			<a href="#" id="orgmapping_add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
			<a href="#" id="orgmapping_edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
			<a href="#" id="orgmapping_save" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save()">保存</a>
			<a href="#" id="orgmapping_delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del()" >删除</a>	
		</div>
		<div data-options="region:'center'">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'west',split:true" style="width:200px">
					<ul id="orgmapping_tree"></ul>
				</div>
				<div data-options="region:'center'">
					<div class="easyui-layout" data-options="fit:true">
						<div data-options="region:'north'" style="height:50%">
							<table id="orgmapping_grid"></table>
						</div>
						<div data-options="region:'center'">
							<form id="orgmapping_form" action="${path}/datafetch/orgmapping/add" method="post">
								<input type="hidden" name="parentCode" />
								<table style="padding:10px;width:500px;">
									<tr>
										<td>核算机构代码:</td>
										<td>
											<input class="easyui-textbox" type="text" name="code" data-options="required:true,validType : ['length[0,60]']" />
										</td>
										<td>核算机构名称:</td>
										<td>
											<input class="easyui-textbox" type="text" name="name" data-options="required:true,validType : ['length[0,60]']"  />
										</td>
									</tr>
									<tr>
										<td>报表机构代码:</td>
										<td>
											<input class="easyui-textbox" type="text" name="repCode" data-options="required:true,validType : ['length[0,60]']"  />
										</td>
										<td>报表机构名称:</td>
										<td>
											<input class="easyui-textbox" type="text" name="repName" data-options="required:true,validType : ['length[0,60]']" />
										</td>
									</tr>
									<tr>
										<td>数据源:</td>
										<td>
											<input class="easyui-combobox" type="text" name="datasourceCode" data-options="required:true,editable:false,url:'${path}/datafetch/datasource/combolist',valueField:'code',textField:'name'"  />
										</td>
										<td>备注:</td>
										<td>
											<input class="easyui-textbox" type="text" name="remark" data-options="validType : ['length[0,200]']" />
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
			

	
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.extend.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	var orgMappingTree,orgMappingGrid;
	$(function() {
		orgMappingTree = $('#orgmapping_tree').tree({
			url : '${path}/datafetch/orgmapping/tree',
			onSelect : function(node){
				orgMappingGrid.datagrid('load',{code : node.id});
			}
		});
		orgMappingGrid = $('#orgmapping_grid').datagrid({
			url : '${path}/datafetch/orgmapping/list',
			singleSelect:true,
			rownumbers : true,
			striped : true,
			fit : true,
			idField : 'code',
			pagination : true,
			pageSize : 10,
			pageList : [10,20,30],
			columns : [ [ 
			{field : 'code',title : '核算机构代码',width : 100,},
			{field : 'name',title : '核算机构名称',width : 100,}, 
			{field : 'repCode',title : '报表机构代码',width : 100,},
			{field : 'repName',title : '报表机构名称',width : 100,},
			{field : 'datasourceCode',title : '数据源',width : 100,},
			{field : 'parentCode',hidden : true,},
			{field : 'remark',hidden : true,},
			] ],
			onSelect : function(rowIndex, rowData){
				disableForm();
				fillForm(rowData);
				setToolBar(true,true,false,true);
			},
			onLoadSuccess : function(data){
				orgMappingGrid.datagrid('selectRow',0);
			}
		});
	});
	
	function add(){
		var node = orgMappingTree.tree('getSelected');
		if(node){
			clearForm();
			enableForm(false);
			setToolBar(false,false,true,false);
			$("input[name='parentCode']").val(node.id);
			$('#orgmapping_form').form({
				url : '${path}/datafetch/orgmapping/add',
				success : function(data){
					if(data == 'success'){
						parent.$.messager.show({ title : "提示",msg: "新增成功！"});
						var newData = getFormData();
						orgMappingTree.tree('append',{
							parent : node.target,
							data : [{
									id : newData.code,
									text : newData.name
									}]
						});
						orgMappingGrid.datagrid('appendRow',newData);
						orgMappingGrid.datagrid('selectRecord',newData.code);
						
					}else{
						parent.$.messager.alert({ title : "提示",msg: data});
					}
				}
			});
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请先选择父节点"});
		}
	}
	
	
	function edit(){
		enableForm(true);
		setToolBar(false,false,true,false);
		$('#orgmapping_form').form({
			url : '${path}/datafetch/orgmapping/edit',
			success : function(data){
				if(data == 'success'){
					parent.$.messager.show({ title : "提示",msg: "修改成功！"});
					var fRow = getFormData();
					var rowIndex = orgMappingGrid.datagrid('getRowIndex',fRow.code);
					orgMappingGrid.datagrid('updateRow',{index : rowIndex,row : fRow});
					orgMappingGrid.datagrid('selectRecord',fRow.code);
					node = orgMappingTree.tree('find',fRow.code);
					orgMappingTree.tree('update',{target : node.target,text : node.name});
				}else{
					parent.$.messager.alert(data);
				}	
			}
		});
	}
	
	function del(){
		var row = orgMappingGrid.datagrid('getSelected');
		if(row){
			parent.$.messager.confirm('提示','删除后无法恢复，您确定要删除此机构及其下级？',function(data){
				if(data){
					$.ajax({
						type : 'post',
						url : '${path}/datafetch/orgmapping/delete',
						data : row,
						success : function(data){
							if(data == 'success'){
								parent.$.messager.show({ title : "提示",msg: "操作成功！"});
								treeRemove(row.code);
							}else{
								parent.$.messager.alert(data);
							}
						},
					});
				}
			});
		}else{
			parent.$.messager.alert({ title : "提示",msg: "请选择要删除的数据！"});
		}
	}
	
	function treeRemove(id){
		var node = orgMappingTree.tree('find',id);
		var parent = orgMappingTree.tree('getParent',node.target);
		orgMappingTree.tree('remove',node.target);
		orgMappingTree.tree('select',parent.target);
	}
	
	
	function save(){
		$('#orgmapping_form').submit();
	}
	
	function disableForm(){
		$("#orgmapping_form .easyui-textbox").textbox({disabled:true});
		$("#orgmapping_form .easyui-combobox").combobox({disabled:true});
	}
	
	function enableForm(isEdit){
		$("#orgmapping_form .easyui-textbox").textbox({disabled:false});
		$("#orgmapping_form .easyui-combobox").combobox('enable');
		if(isEdit){
			$("input[name='code']").prev().attr('readonly','readonly');
		}else{
			$("input[name='code']").prev().removeAttr('readonly');
		}
	}
	
	function clearForm(){
		$("#orgmapping_form .easyui-textbox").textbox('clear');
		$("#orgmapping_form .easyui-combobox").combobox('clear');
	}
	
	function fillForm(rowData){
		$("#orgmapping_form").form('load',rowData);
	}
	
	function setToolBar(addFlag,editFlag,saveFlag,delFlag){
		$('#orgmapping_add').linkbutton({disabled : !addFlag});
		$('#orgmapping_edit').linkbutton({disabled : !editFlag});
		$('#orgmapping_save').linkbutton({disabled : !saveFlag});
		$('#orgmapping_delete').linkbutton({disabled : !delFlag});
	}
	
	function getFormData(){
		var o = {};
		o.name = $("input[name='name']").val();
		o.code = $("input[name='code']").val();
		o.repCode = $("input[name='repCode']").val();
		o.repName = $("input[name='repName']").val();
		o.datasourceCode = $("input[name='datasourceCode']").val();
		o.remark = $("input[name='remark']").val();
		o.parentCode = $("input[name='parentCode']").val();
		return o;
	}
	
	
	
	
	</script>
</body>

<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>字典管理</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
</head>
<body>
	
	<div class="easyui-layout">
		<div data-options="region:'west',split:true " style="width:200px;">
			<div id="tree_tb" class="easyui-panel" data-options="border:false">
				<@shiro.hasPermission name = "sys:dict:add">
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add_tree()">新增</a>
				</@shiro.hasPermission>
				<@shiro.hasPermission name = "sys:dict:edit">
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit_tree()">编辑</a>
				</@shiro.hasPermission>
				<@shiro.hasPermission name = "sys:dict:delete">
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="remove_tree()">删除</a>
				</@shiro.hasPermission>
			</div>
			<ul id="dict_tree"></ul>
			<div id="tree_menu" class="easyui-menu" style="width:120px;">
				<@shiro.hasPermission name = "sys:dict:add">
				<div onclick="add_tree()" data-options="iconCls:'icon-add'">添加</div>
				</@shiro.hasPermission>
				<@shiro.hasPermission name = "sys:dict:edit">
				<div onclick="edit_tree()" data-options="iconCls:'icon-edit'">修改</div>
				</@shiro.hasPermission>
				<@shiro.hasPermission name = "sys:dict:delete">
				<div onclick="remove_tree()" data-options="iconCls:'icon-remove'">删除 </div>
				</@shiro.hasPermission>
			</div>
			<div id="dlg"></div>
		</div>
		<div data-options="region:'center' " >
			<table id="item_table"></table>
			<div id="item_tb" style="padding:5px">
				<div>
					<a href="#" class="easyui-linkbutton item_toolbar" data-options="iconCls:'icon-add',plain:true,disabled:true" onclick="add_item()">新增</a>
					<a href="#" class="easyui-linkbutton item_toolbar" data-options="iconCls:'icon-edit',plain:true,disabled:true" onclick="edit_item()" >编辑</a>
					<a href="#" class="easyui-linkbutton item_toolbar" data-options="iconCls:'icon-remove',plain:true,disabled:true" onclick="remove_item()">删除</a>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.extend.js"></script>
	<script type="text/javascript">
		var dictTree,dlg,selectNode,itemTable;
		$(function() {
			$('.easyui-layout').layout({
				fit : true,
			});
			
			dictTree = $('#dict_tree').tree({
	        	url:'${path}/sys/dict/tree',
	        	animate: true,
	        	onContextMenu : function(e,node){
	        		e.preventDefault();
	        		$(this).tree('select',node.target);
	        		$('#tree_menu').menu('show',{
						left: e.pageX,
						top: e.pageY
					});
	        	},
	        	onClick : function(node){
	        		if(dictTree.tree('isLeaf',node.target)){
	        			enableItemToolBar();
	        			itemTable.datagrid('load',{dictId : node.id});
	        		}else{
	        			disableItemToolBar();
	        			itemTable.datagrid('loadData',{"total":0,"rows":[]});
	        		}
	        		
	        	},
    		});
    		
    		
    		itemTable = $('#item_table').datagrid({
				url : '${path}/sys/dict/itemQuery',
				pagination : true,
				singleSelect:true,
				fit : true,
				pageSize : 5,
				pageList : [ 5, 10 ],
				rownumbers : true,
				striped : true,
				columns : [ [ 
				{field : 'dictId',hidden : true,},
				{field : 'value',title : ' 值',width : 100,sortable : true,},
				{field : 'text',title : ' 文本',width : 100,sortable : true,}, 
				{field : 'remark',title : ' 备注',width : 100,sortable : true,},
				] ],
				toolbar : '#item_tb'
			});
		});
		
		function add_tree(){
			selectNode = dictTree.tree('getSelected');
			if(selectNode){
				dlg = $('#dlg').dialog({
					width : 250,
					height : 250,
					href : '${path}/sys/dict/add?parentId='+selectNode.id,
					title: '新增字典类型',
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
			}else{
				parent.$.messager.alert({ title : "提示",msg: "请选择要新增节点数据！" });
			}
		}
		
		function edit_tree(){
			selectNode = dictTree.tree('getSelected');
			if(selectNode){
				dlg = $('#dlg').dialog({
					width : 250,
					height : 250,
					href : '${path}/sys/dict/edit?id='+selectNode.id,
					title: '新增字典类型',
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
				parent.$.messager.alert({ title : "提示",msg: "请选择要编辑节点数据！" });
			}
		}
		
		function remove_tree(){
			selectNode = dictTree.tree('getSelected');
			if(selectNode){
				if(selectNode.id == 'ROOT'){
					parent.$.messager.alert({ title : "提示",msg: "根节点无法删除！" });
					return;
				}
				if(dictTree.tree('isLeaf',selectNode)){
					parent.$.messager.confirm('提示','删除后无法回复，您确定要删除？',function(isOk){
						if(isOk){
							$.ajax({
								type : 'post',
								url : '${path}/sys/dict/delete?id='+selectNode.id,
								success : function(data){
									if(data == 'success'){
										dictTree.tree('remove',selectNode.target);
										parent.$.messager.show({ title : "提示",msg: "操作成功！"});
									}else{
										parent.$.messager.alert(data);
									}
								}
							});
						}
					});
				}else{
					parent.$.messager.alert("请先删除子节点！");
				}
			}else{
				parent.$.messager.alert({ title : "提示",msg: "请选择要删除节点数据！" });
			}
		
		}
		
		function add_item(){
			selectNode = dictTree.tree('getSelected');
			dlg = $('#dlg').dialog({
				width : 250,
				height : 250,
				href : '${path}/sys/dict/itemAdd?dictId='+selectNode.id,
				title: '新增'+selectNode.text+'数据',
				modal: true,
				buttons:[{
					text:'保存',
					handler:function(){
						$('#item_add_form').submit(); 
					}
				},{
					text:'取消',
					handler:function(){
							dlg.panel('close');
						}
				}]
			});
		}
		
		function edit_item(){
			selectNode = dictTree.tree('getSelected');
			var row = itemTable.datagrid('getSelected');
			if(row){
				dlg = $('#dlg').dialog({
					width : 250,
					height : 250,
					href : '${path}/sys/dict/itemEdit?dictId='+row.dictId + '&value=' + row.value,
					title: '新增'+selectNode.text+'数据',
					modal: true,
					buttons:[{
						text:'保存',
						handler:function(){
							$('#item_edit_form').submit(); 
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
		
		function remove_item(){
			var row = itemTable.datagrid('getSelected');
			if(row){
				parent.$.messager.confirm('提示','删除后无法回复，您确定要删除？',function(isOk){
					if(isOk){
						$.ajax({
							type : 'post',
							url : '${path}/sys/dict/itemDelete',
							data : row,
							success : function(data){
								if(data == 'success'){
									itemTable.datagrid('reload');
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
		
		
		function enableItemToolBar(){
			$('.item_toolbar').linkbutton('enable');
		}
		
		function disableItemToolBar(){
			$('.item_toolbar').linkbutton('disable');
		}
		
	</script>
</body>

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
			<ul id="dict_tree"></ul>
			<div id="tree_menu" class="easyui-menu" style="width:120px;">
				<div onclick="add_tree()" data-options="iconCls:'icon-add'">添加</div>
				<div onclick="edit_tree()" data-options="iconCls:'icon-edit'">修改</div>
				<div onclick="remove_tree()" data-options="iconCls:'icon-remove'">删除 </div>
			</div>
			<div id="dlg"></div>
		</div>
		<div data-options="region:'center' " >
			haha
		</div>
	</div>
	
	
	
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript">
		var dictTree,dlg,selectNode;
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
    		});
		});
		
		function add_tree(){
			selectNode = dictTree.tree('getSelected');
			if(selectNode){
				dlg = $('#dlg').dialog({
					width : 250,
					height : 250,
					href : '${path}/sys/dict/edit?parentId='+selectNode.id,
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
				parent.$.messager.show({ title : "提示",msg: "请选择要新增节点数据！" });
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
				parent.$.messager.show({ title : "提示",msg: "请选择要编辑节点数据！" });
			}
		}
		
		function remove_tree(){
			selectNode = dictTree.tree('getSelected');
			if(selectNode){
				if(dictTree.tree('isLeaf',selectNode)){
					parent.$.messager.confirm('提示','删除后无法回复，您确定要删除？',function(){
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
					});
				}else{
					parent.$.messager.alert("请先删除子节点！");
				}
			}else{
				parent.$.messager.show({ title : "提示",msg: "请选择要删除节点数据！" });
			}
		
		}
		
		
		
	</script>
</body>

<#assign path=request.contextPath />
<form id="edit_button_form" action="${path}/sys/permission/edit" method="post">
	<input type="hidden" name="id" value="${(permission.id)!}"/>
	<input type="hidden" name="type" value="${(permission.type)!}"/>
	<table style="width:100%;padding:10px;">
    	<tr>
			<td>按钮名称:</td>
			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" value="${(permission.name)!}" /></td>
		</tr>
		<tr>
			<td>按钮图标:</td>
			<td><input id="icon_selector" type="text" name="icon"  value="${(permission.icon)!}" /></td>
		</tr>
		<tr>
			<td>权限代码:</td>
			<td><input class="easyui-textbox" type="text" name="code" data-options="required:true"  value="${(permission.code)!}" /></td>
		</tr>
		<tr>
			<td>所属菜单:</td>
			<td><input id="menu_tree" class="easyui-combotree" type="text" name="parentId" data-options="required:true,method:'get',url:'${path}/sys/permission/menuTree'"  value="${(permission.parentId)!}" /></td>
		</tr>
		<tr>
			<td>菜单序号:</td>
			<td><input class="easyui-numberbox" type="text" name="sort" data-options="min:0"  value="${(permission.sort)!}" /></td>
		</tr>
		<tr>
			<td>菜单描述:</td>
			<td><input class="easyui-textbox" type="text" name="remark" value="${(permission.remark)!}" /></td>
		</tr>
	</table>
   
</form>
<script type="text/javascript">
$(function(){
	$('#edit_button_form').form({
		onSubmit: function(){    
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },
		success : function(data){
			if(data == 'success'){
				permission_table.treegrid('reload');
				dlg.panel('close');
				parent.$.messager.show({ title : "提示",msg: "操作成功！"});
			}else{
				parent.$.messager.alert(data);
				return false;
			}
		}	
	});
	$('#icon_selector').combo({
		panelWidth : 300,
		panelHeight : 200,
		editable:false,
	});
	
	$('#menu_tree').combotree({
		onBeforeSelect : function(node) {  
	        if(node.type != 'MENU'){
	            return false;
	    	}
	    }  
	});
	
	createIconList('#icon_selector');
	
	
});
</script>
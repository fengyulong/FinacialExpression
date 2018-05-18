<#assign path=request.contextPath />
<form id="edit_form" action="${path}/sys/permission/edit" method="post">
	<input type="hidden" name="id" value="${(permission.id)!}"/>
	<input type="hidden" name="type" value="${(permission.type)!}"/>
	<table style="width:100%;padding:10px;">
    	<tr>
			<td>分组名称:</td>
			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" value="${(permission.name)!}" /></td>
		</tr>
		<tr>
			<td>分组图标:</td>
			<td><input id="icon_selector" type="text" name="icon"  value="${(permission.icon)!}" /></td>
		</tr>
		<tr>
			<td>分组序号:</td>
			<td><input class="easyui-numberbox" type="text" name="sort" data-options="min:0"  value="${(permission.sort)!}" /></td>
		</tr>
		<tr>
			<td>分组描述:</td>
			<td><input class="easyui-textbox" type="text" name="remark" value="${(permission.remark)!}" /></td>
		</tr>
	</table>
   
</form>
<script type="text/javascript">
$(function(){
	$('#edit_form').form({
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
	
	createIconList('#icon_selector');
	
	
});
</script>
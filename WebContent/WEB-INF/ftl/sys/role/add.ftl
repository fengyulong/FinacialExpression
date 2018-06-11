<#assign path=request.contextPath />
<form id="add_form" action="${path}/sys/role/add" method="post">
	<input type="hidden" name="id" value="${(role.id)!}"/>
	<table style="width:100%;padding:10px;">
    	<tr>
			<td>角色代码:</td>
			<td><input class="easyui-textbox" type="text" name="code" data-options="required:true" value="${(role.code)!}" /></td>
			
		</tr>
		<tr>
			<td>角色名称:</td>
			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" value="${(role.name)!}" /></td>
		</tr>
		<tr>
			<td>排序:</td>
			<td><input class="easyui-numberbox" type="text" name="sort" data-options="min:0" value="${(role.sort)!}" /></td>
		</tr>
		<tr>
			<td>备注:</td>
			<td><input class="easyui-textbox" type="text" name="remark" data-options="multiline:true" value="${(role.remark)!}" /></td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	$('#add_form').form({
		onSubmit: function(){    
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },
		success : function(data){
			if(data == 'success'){
				role_table.datagrid('reload');
				dlg.panel('close');
				parent.$.messager.show({ title : "提示",msg: "操作成功！"});
			}else{
				parent.$.messager.alert({ title : "错误",msg: data});
				return false;
			}
		}	
	});
</script>
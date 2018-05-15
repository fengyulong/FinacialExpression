<#assign path=request.contextPath />
<form id="edit_form" action="${path}/sys/dict/itemEdit" method="post">
	<input type="hidden" name="dictId" value="${(dictItem.dictId)!}" />
	<table style="width:100%;padding:10px;">
    	<tr>
			<td>代码:</td>
			<td><input class="easyui-textbox" type="text" name="value" data-options="required:true,validType:'syscode',invalidMessage:'只能输入字母、数字或下划线'" <#if (dictItem.value)??>readonly="readonly"</#if> value="${(dictItem.value)!}" /></td>
		</tr>
		<tr>
			<td>名称:</td>
			<td><input class="easyui-textbox" type="text" name="text" data-options="required:true"  value="${(dictItem.text)!}" /></td>
		</tr>
		<tr>
			<td>备注:</td>
			<td><input class="easyui-textbox" type="text" name="remark" data-options="multiline:true"  value="${(dictItem.remark)!}" /></td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	$('#edit_form').form({
		onSubmit: function(){    
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },
		success : function(data){
			if(data == 'success'){
				itemTable.datagrid('reload');
				dlg.panel('close');
				parent.$.messager.show({ title : "提示",msg: "操作成功！"});
			}else{
				parent.$.messager.alert(data);
				return false;
			}
		}	
	});
	
	

	
</script>
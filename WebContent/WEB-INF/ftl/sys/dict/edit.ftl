<#assign path=request.contextPath />
<form id="edit_form" action="${path}/sys/dict/edit" method="post">
	<input type="hidden" name="parentId" value="${(dict.parentId)!}" />
	<table style="width:100%;padding:10px;">
    	<tr>
			<td>代码:</td>
			<td><input class="easyui-textbox" type="text" name="id" data-options="required:true,validType:'syscode',invalidMessage:'只能输入字母、数字或下划线'" <#if (dict.id)??>readonly="readonly"</#if> value="${(dict.id)!}" /></td>
		</tr>
		<tr>
			<td>名称:</td>
			<td><input class="easyui-textbox" type="text" name="text" data-options="required:true"  value="${(dict.text)!}" /></td>
		</tr>
		<tr>
			<td>备注:</td>
			<td><input class="easyui-textbox" type="text" name="remark" data-options="multiline:true"  value="${(dict.remark)!}" /></td>
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
				dlg.panel('close');
				<#if !(dict.id)??>
				dictTree.tree('append',{
					parent:selectNode.target,
					data:[{
						id : $('input[name="id"]').val(),
						text : $('input[name="text"]').val()
					}]
				});
				</#if>
				<#if (dict.id)??>
				dictTree.tree('update',{
					target: selectNode.target,
					text : $('input[name="text"]').val()
				});
				</#if>
				parent.$.messager.show({ title : "提示",msg: "操作成功！"});
			}else{
				parent.$.messager.alert(data);
				return false;
			}
		}	
	});
	
	function fnKeyUp(){
		this.value=this.value.toUpperCase();
    }

	
</script>
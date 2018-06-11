<#assign path=request.contextPath />
<form id="add_form" action="${path}/sys/dict/add" method="post">
	<input type="hidden" name="parentId" value="${(dict.parentId)!}" />
	<table style="width:100%;padding:10px;">
    	<tr>
			<td>代码:</td>
			<td><input class="easyui-textbox" type="text" name="id" data-options="required:true,validType:'syscode',invalidMessage:'只能输入字母、数字或下划线'"  value="${(dict.id)!}" /></td>
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
	$('#add_form').form({
		onSubmit: function(){    
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },
		success : function(data){
			if(data == 'success'){
				dlg.panel('close');
				dictTree.tree('append',{
					parent:selectNode.target,
					data:[{
						id : $('input[name="id"]').val(),
						text : $('input[name="text"]').val()
					}]
				});
				parent.$.messager.show({ title : "提示",msg: "操作成功！"});
			}else{
				parent.$.messager.alert({ title : "错误",msg: data});
				return false;
			}
		}	
	});
	
	function fnKeyUp(){
		this.value=this.value.toUpperCase();
    }

	
</script>
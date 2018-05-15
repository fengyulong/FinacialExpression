<#assign path=request.contextPath />
<form id="edit_form" action="${path}/sys/user/edit" method="post">
	<input type="hidden" name="id" value="${(user.id)!}"/>
	<table style="width:100%;padding:10px;">
    	<tr>
			<td>用户名:</td>
			<td><input class="easyui-textbox" type="text" name="username" data-options="required:true" value="${(user.username)!}" /></td>
			<td>昵称:</td>
			<td><input class="easyui-textbox" type="text" name="nickname" data-options="required:true" value="${(user.nickname)!}" /></td>
		</tr>
		<#if !user??>
		<tr>
			<td>密码:</td>
			<td><input class="easyui-textbox" type="password" name="password" data-options="required:true" /></td>
			<td>重复密码:</td>
			<td><input class="easyui-textbox" type="password" name="repeat_password" data-options="required:true" /></td>
		</tr>
		</#if>
		<tr>
			<td>邮箱:</td>
			<td><input class="easyui-textbox" type="text" name="email" data-options="required:true,validType:'email'"  value="${(user.email)!}" /></td>
			<td>手机号:</td>
			<td><input class="easyui-textbox" type="text" name="mobile" data-options="required:true"  value="${(user.mobile)!}" /></td>
		</tr>
		<tr>
			<td>性别:</td>
			<td>
				<input class="easyui-combobox" name="sex"  value="${(user.sex)!}" data-options="valueField:'value',textField:'text',method:'get',url:'${path}/sys/dict/itemQuery?dictId=USER_SEX'" />
			</td>
			<td>状态:</td>
			<td><input class="easyui-textbox" type="text" name="state" data-options="required:true"  value="${(user.state)!}" /></td>
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
				dg.datagrid('reload');
				d.panel('close');
				parent.$.messager.show({ title : "提示",msg: "操作成功！"});
			}else{
				parent.$.messager.alert(data);
				return false;
			}
		}	
	});
</script>
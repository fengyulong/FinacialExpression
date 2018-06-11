<#assign path=request.contextPath />
<table id="role_table"></table>


<script type="text/javascript">
	role_table = $('#role_table').datagrid({
		url : '${path}/sys/role/queryAll',
		rownumbers : true,
		striped : true,
		checkbox : true,
		columns : [ [ 
		{field : 'id',title:'选择',checkbox:true,},
		{field : 'name',title : '角色名称',width : 100,sortable : true,},
		{field : 'code',title : '角色代码',width : 100,sortable : true,}, 
		{field : 'remark',title : '备注',width : 100,sortable : false,},
		] ],
		onLoadSuccess : function(data){ 
			var roleIdArr = new Array();
			roleIdArr.push('test');
			<#list userRoleList as userRole>
			roleIdArr.push('${userRole.roleId}');
			</#list>
			var rows = data.rows;
			for(var i=0;i<rows.length;i++){
				if($.inArray(rows[i].id,roleIdArr)!=-1){
					role_table.datagrid('checkRow',i);
				}
			}
		}
	});
</script>
<#assign path=request.contextPath />
<ul id="permission_tree"></ul>


<script type="text/javascript">
	permissionTree = $('#permission_tree').tree({
		url : '${path}/sys/permission/permissionTree?roleId=${roleId!}',
		method : 'get',
		checkbox : true,
		cascadeCheck : false,
	});
	

</script>

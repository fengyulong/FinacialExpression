$(function() {
	$('#user_table').datagrid({
		url : '/expression/user/query',
		pagination : true,
		pageSize : 5,
		pageList : [ 5, 10 ],
		rownumbers : true,
		striped : true,
		columns : [ [ {
			field : 'username',
			title : ' 用户名',
			width : 100,
			sortable : true,
		}, {
			field : 'password',
			title : ' 密码',
			width : 100,
			sortable : true,
		} ] ],
		toolbar : '#tb'
	});

});
$(function() {
	$('.easyui-layout').layout({
		fit : true,
	});
	$('.easyui-linkbutton').linkbutton({
		toggle : true
	});

	$('.easyui-linkbutton').on('click', function() {
		$('.easyui-linkbutton').linkbutton('unselect');
		$(this).linkbutton('select');
		var option = $(this).linkbutton('options');
		if ($('.easyui-tabs').tabs('exists', option.text)) {
			$('.easyui-tabs').tabs('select', option.text)
		} else {
			$('.easyui-tabs').tabs('add', {
				title : option.text,
				href : '/expression/user',
				closable : true,
				tools : [ {
					iconCls : 'icon-mini-refresh',
					handler : function() {
						alert('refresh');
					}
				} ],
			});
		}
	});

});
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
				content : '<iframe src="'+$(this).attr('url')+'" style="width:100%;height:100%;margin:0px;border:none" scrolling="no"/>',
				closable : true,
			});
		}
	});

});



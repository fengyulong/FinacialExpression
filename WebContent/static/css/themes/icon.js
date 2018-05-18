


function createIconList(combo){
	var iconList = [ 'icon-blank', 'icon-add', 'icon-edit', 'icon-clear',
	         		'icon-remove', 'icon-save', 'icon-cut', 'icon-ok', 'icon-no',
	         		'icon-cancel', 'icon-reload', 'icon-search', 'icon-print', 'icon-help',
	         		'icon-undo', 'icon-redo', 'icon-back', 'icon-sum', 'icon-tip',
	         		'icon-filter', 'icon-man', 'icon-lock', 'icon-more', 'icon-mini-add',
	         		'icon-mini-edit', 'icon-mini-refresh', 'icon-large-picture',
	         		'icon-large-clipart', 'icon-large-shapes', 'icon-large-smartart',
	         		'icon-large-chart', 'icon-login' ];
	
	var html = '<ul style="padding:10px;margin:0;">';
	for(var i=0;i<iconList.length;i++){
		html += '<li onclick="selectIcon(\''+combo+'\',$(this))" title="' + iconList[i] + '" style="list-style-type: none;float: left;cursor: pointer;margin: 2px;width: 20px;height: 20px;">';
		html += '<span style="line-height: 16px;padding-left: 16px;display: inline-block;" class="' + iconList[i] + '">';
		html += '&nbsp;';
		html += '</span>';
		html += '</li>';
	}
	html += '</ul>';
	$(html).appendTo($(combo).combo('panel'));
	var value =  $(combo).combo('getValue');
	if(value != ''){
		$(combo).next().find('a.combo-arrow').addClass(value);
	}
}

function selectIcon(combo,$li){
	var oldValue = $(combo).combo('getValue');
	var newValue = $li.attr('title');
	$(combo).combo('setValue',newValue);
	$(combo).combo('setText',newValue);
	$(combo).combo('hidePanel');
	$(combo).next().find('a.combo-arrow').removeClass(oldValue);
	$(combo).next().find('a.combo-arrow').addClass(newValue);
}

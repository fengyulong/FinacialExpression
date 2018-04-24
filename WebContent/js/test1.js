$(function() {
	pageInit();
});
function pageInit() {

	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	var parent_column = $(grid_selector).closest('[class*="col-"]');
	// resize to fit page size
	$(window).on('resize.jqGrid', function() {
		$(grid_selector).jqGrid('setGridWidth', parent_column.width());
	})

	// resize on sidebar collapse/expand
	$(document).on(
			'settings.ace.jqGrid',
			function(ev, event_name, collapsed) {
				if (event_name === 'sidebar_collapsed'
						|| event_name === 'main_container_fixed') {
					// setTimeout is for webkit only to give time for DOM
					// changes and then redraw!!!
					setTimeout(function() {
						$(grid_selector).jqGrid('setGridWidth',
								parent_column.width());
					}, 20);
				}
			})

	// if your grid is inside another element, for example a tab pane, you
	// should use its parent's width:

	$(window).on('resize.jqGrid', function() {
		var parent_width = $(grid_selector).closest('.tab-pane').width();
		$(grid_selector).jqGrid('setGridWidth', parent_width);
	})
	// and also set width when tab pane becomes visible
	$('#myTab a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		if ($(e.target).attr('href') == '#mygrid') {
			var parent_width = $(grid_selector).closest('.tab-pane').width();
			$(grid_selector).jqGrid('setGridWidth', parent_width);
		}
	})

	jQuery("#grid-table").jqGrid(
			{
				url : 'http://127.0.0.1:7777/expression/json',
				datatype : "json",
				colNames : [ 'Inv No', '日期', '客户端', 'Amount', 'Tax',
						'Total', 'Notes' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					width : 55
				}, {
					name : 'invdate',
					index : 'invdate',
					width : 90
				}, {
					name : 'name',
					index : 'name asc, invdate',
					width : 100
				}, {
					name : 'amount',
					//index : 'amount',
					width : 80,
					align : "right"
				}, {
					name : 'tax',
					index : 'tax',
					width : 80,
					align : "right"
				}, {
					name : 'total',
					index : 'total',
					width : 80,
					align : "right"
				}, {
					name : 'note',
					index : 'note',
					width : 150,
					sortable : false
				} ],
				rowNum : 5,
				rowList : [ 5, 10, 20 ],
				pager : '#grid-pager',
				sortname : 'id',
				mtype : "post",
				viewrecords : true,
				sortorder : "desc",
				caption : "JSON Mapping",
				height : '100%',
				rownumbers : true,
				toolbar : [ true, "top" ],
				loadComplete : function() {
					var table = this;
					setTimeout(function() {
						// styleCheckbox(table);

						// updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0);
				},
				loadonce : true
			});
	jQuery("#grid-table").jqGrid('navGrid', '#grid-pager', { // navbar
																// options
		edit : true,
		editicon : 'ace-icon fa fa-pencil blue',
		add : true,
		addicon : 'ace-icon fa fa-plus-circle purple',
		del : true,
		delicon : 'ace-icon fa fa-trash-o red',
		search : true,
		searchicon : 'ace-icon fa fa-search orange',
		refresh : true,
		refreshicon : 'ace-icon fa fa-refresh green',
		view : true,
		viewicon : 'ace-icon fa fa-search-plus grey',
	});
	// replace icons with FontAwesome icons like above
	function updatePagerIcons(table) {
		var replacement = {
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
				.each(
						function() {
							var icon = $(this);
							var $class = $.trim(icon.attr('class').replace(
									'ui-icon', ''));

							if ($class in replacement)
								icon.attr('class', 'ui-icon '
										+ replacement[$class]);
						})
	}

	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({
			container : 'body'
		});
		$(table).find('.ui-pg-div').tooltip({
			container : 'body'
		});
	}

	$(window).triggerHandler('resize.jqGrid');
}
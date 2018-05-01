<#assign path=request.contextPath />
<table id="user_table"></table>
<div id="dlg"></div>
<div id="tb" style="padding:5px">
	<div>
		<input type="text" class="easyui-textbox" data-options="prompt:'用户名'"/>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
	</div>
	<div>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
</div>

<script type="text/javascript" src="${path}/static/js/user.js"></script>

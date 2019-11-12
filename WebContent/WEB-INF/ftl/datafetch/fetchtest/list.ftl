<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>取数测试</title>
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/static/css/themes/icon.css">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" style="width:200px">
			<ul id="orgmapping_tree"></ul>
		</div>
		<div data-options="region:'center'">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north'" style="height:50%;">
					<div class="easyui-tabs" data-options="fit:true,plain:true" style="width:100%;">
						<div title="固定公式" style="padding:10px 0 0 10px">
							<form id="fixForm" action="${path}/datafetch/fetchtest/fix" method="post">
								<input type="hidden" type="text" name="unitCode" />
								<input class="easyui-datebox" type="text" name="startDate" label="开始日期:" labelPosition="top" value="2018-01-01" data-options="required:true" style="width:400px"/>
								<input class="easyui-datebox" type="text" name="endDate" label="结束日期:" labelPosition="top" value="2018-12-01" data-options="required:true" style="width:400px"/>
								<input class="easyui-textbox" type="text" name="expression" label="取数公式:" labelPosition="top" value="BDB(1,1,segment1)" data-options="required:true"  style="width:400px"/>
								<div style="margin: 20px">
									<a href="#" class="easyui-linkbutton" style="width:200px" onclick="fixFetch()">执行取数</a>
								</div>
							</form>
						</div>
						<div title="浮动公式" style="padding:10px 0 0 10px">
							<form id="floatForm" action="${path}/datafetch/fetchtest/float" method="post">
								<input type="hidden" name="unitCode" />
								<input class="easyui-datebox" name="startDate" label="开始日期:" labelPosition="top" value="2018-01-01" editable="false" style="width:400px"/>
								<input class="easyui-datebox" name="endDate" label="结束日期:" labelPosition="top" value="2018-12-01" data-options="required:true" style="width:400px"/>
								<input class="easyui-textbox" name="expression" label="浮动公式:" labelPosition="top" value="FBDB(1,52)" data-options="required:true" style="width:400px"/>
								<input class="easyui-textbox" name="colExpression" label="列值公式:" labelPosition="top" value="val(segment1),val(segment2)" data-options="required:true" style="width:400px"/>
								<div style="margin: 20px">
									<a href="#" class="easyui-linkbutton" style="width:200px" onclick="floatFetch()">执行取数</a>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div data-options="region:'center'">
					<div id="result_grid" style="padding-left: 10px"></div>
				</div>
			</div>
		</div>

		<div data-options="region:'center',split:true" >

		</div>
	</div>
	
	
			

	
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/jquery.easyui.extend.js"></script>
	<script type="text/javascript" src="${path}/static/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		var orgMappingTree
		$(function() {
			orgMappingTree = $('#orgmapping_tree').tree({
				url : '${path}/datafetch/orgmapping/tree',
				onSelect : function(node){
					$("input[name='unitCode']").val(node.id);
				}
			});

			$('#fixForm').form({
				success : function(data){
					var resultObj = JSON.parse(data);
					var colName = resultObj.expression[0];
					var colValue = resultObj.values[0];
					$('#result_grid').datagrid({
						fit : true,
						columns : [[
							{field : 'name',title : colName,width : 200,}
						]],
						data : [
							{'name' : colValue}
						]
					});
				}
			});
			$("#floatForm").form({
				success : function(data){
					var resultObj = JSON.parse(data);
					var rowCount = resultObj.rowCount;
					var colCount = resultObj.colResults.length;
					var colNameArray = new Array();
					var dataArray = new Array();
					for(var i=0;i<colCount;i++){
						colNameArray[i] = {field : 'f'+i,title : resultObj.colResults[i].expression[0]};
					}
					for(var j=0;j<rowCount;j++){
						var dataObj = {};
						for(var k=0;k<colCount;k++){
							dataObj['f'+k] = resultObj.colResults[k].values[j];
						}
						dataArray[j] = dataObj;
					}
					$('#result_grid').datagrid({
						fit : true,
						columns : [colNameArray],
						data : dataArray
					});
				}
			});
		});
		
		function floatFetch(){
			var node = orgMappingTree.tree('getSelected');
			if(node){
				$('#floatForm').submit();
			}else{
				parent.$.messager.alert({ title : "提示",msg: "请先选择组织机构"});
			}
		}
		
		function fixFetch() {
			var node = orgMappingTree.tree('getSelected');
			if(node){
				$('#fixForm').submit();
			}else{
				parent.$.messager.alert({ title : "提示",msg: "请先选择组织机构"});
			}
		}
	
	
	</script>
</body>

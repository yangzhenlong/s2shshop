<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>

	<script type="text/javascript">
		$(function(){
			$('#dg').datagrid({
			    url:'datagrid_data.json',//获取datagrid_data.json文件中的内容
			    //真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动
			    fitColumns:true,
			    //默认排序数序，只能是'asc'或'desc'。
			    order:'desc',
			    //是否使用排列
			    sortable:true,
			    //斑马线
			    striped:true,
			    loadMsg:'数据加载中......',
			    //分页栏
			    pagination:true,
			    //行号
			    rownumbers:true,
			    //同列属性，但是这些列将会被冻结在左侧
			    frozenColumns:[[
			    	{field:'combox',checkbox:true},
			    	{field:'productid',title:'编号',width:100}
			    ]],
			    //普通列
			    columns:[[
			        {field:'productname',title:'产品名称',width:100,
			        	//使用formatter属性，设置当前列的title提示
			        	formatter:function(value,row,index){
			        		return "<span title='"+value+"'>" +value+ "</span>";
			        	}
			        },
			        {field:'unitcost',title:'Price',width:100,align:'right',
			        	//使用styler属性，设置该列满足条件的值的样式
			        	styler: function(value,row,index){
							if (value < 20){
								return 'background-color:#fff;color:red;';
							}
						}
			        }
			    ]]
			});
		});
	</script>
  </head>
  
  <body>
  	<table id="dg"></table>
  </body>
</html>

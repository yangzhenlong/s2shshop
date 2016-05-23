<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
	<style type="text/css">
		/*搜索框 调整位置*/
		.searchbox {
			  margin: -3;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$('#dg').datagrid({
			    url:'product_queryJoinCategory.action',//获取datagrid_data.json文件中的内容
			    //真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动
			    fitColumns:true,
			    //如果为true，则只允许选择一行
			    singleSelect:false,
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
			    //-----指定id为标识字段，在删除、更新的时候有用。翻页后选中的记录不会丢失-----
			    idField:'id',
			    //工具栏
			    toolbar: [{
					iconCls: 'icon-add',
					text:'添加商品',
					handler: function(){
						//调用父页面的部件，使用parent
						parent.$("#win").window({
							title:'添加商品',
							width:500,
							height:600,
							//下面的<div id='win' />弹出框要放到querycategory.jsp的父页面index.jsp，不然弹框时，只能锁当前页面，不能锁定整个页面
							content:'<iframe src="send_product_save.action" frameborder="0" width="100%" height="100%"/>'
						});
					}
				},'-',{
					iconCls: 'icon-edit',
					text:'编辑商品',
					handler: function(){
						var rows = $("#dg").datagrid("getSelections");
						//只能更新一行数据
						if(rows.length!=1){
							$.messager.show({
								title:'错误提示',
								msg:'请选择一行记录进行更新',
								timeout:2000,
								showType:'slide'
							});
						}else{
							//调用父页面的部件，使用parent
							parent.$("#win").window({
								title:'编辑商品',
								width:400,
								height:330,
								//下面的<div id='win' />弹出框要放到query.jsp的父页面index.jsp，不然弹框时，只能锁当前页面，不能锁定整个页面
								content:'<iframe src="send_product_update.action" frameborder="0" width="100%" height="100%"/>'
							});
						}
					}
				},'-',{
					iconCls: 'icon-remove',
					text:'删除商品',
					handler: function(){
						//alert('删除类别');
						//判断是否有选中行
						var rows = $("#dg").datagrid("getSelections");
						//如果没有选中，则返回一个空数组
						if(rows.length == 0){
							$.messager.show({
								title:'错误提示',
								msg:'至少选择一行',
								timeout:2000,
								showType:'slide'
							});
							
						}else{
							//提示“确认删除”，执行删除操作
							$.messager.confirm('确认删除', '是否删除选中记录？', function(r){
								if (r){
								    // 1.获取选中记录;
								    var ids="";
								    for(var i=0;i<rows.length;i++){
								    	ids += rows[i].id + ",";
								    }
								    //2.拼接id的值（1,2,3,5,...）
								    ids = ids.substring(0,ids.lastIndexOf(","));
								    //alert(ids);
								    //3.发送ajax请求,设置回调函数
								    $.post("product_deleteByIds.action",{ids:ids},function(result){
								    	//回调函数
								    	if(result=="true"){//true是从后台的流返回的数据
								    		//alert("成功删除"+ ids.split(",").length +"条数据");
								    		//取消选中的所有行，不然datagrid会一直选中刚才删除的行
								    		$("#dg").datagrid("uncheckAll");
								    		//重新加载当前页数据：reload
								    		$('#dg').datagrid('reload');
								    	}else{
								    		$.messager.show({
												title:'删除异常',
												msg:'删除失败，请确认',
												timeout:2000,
												showType:'slide'
											});
								    	}
								    });
								}
							});
						}
					}
				},'-',{
					iconCls: 'icon-help',
					text:'帮助',
					handler: function(){
						alert('帮助');
					}
				},'-',{
					text:"<input id='ss' name='search'/>" //搜索框
				}],
			    
			    //同列属性，但是这些列将会被冻结在左侧
			    frozenColumns:[[
			    	{field:'combox',checkbox:true},
			    	{field:'id',title:'编号',width:100}
			    ]],
			    //查询条件，要传递给后台的参数
			    queryParams:{name:''},
			    //普通列
			    columns:[[
			        {field:'name',title:'商品名称',width:100,
			        	//使用formatter属性，设置当前列的title提示
			        	formatter:function(value,row,index){
			        		return "<span title='"+value+"'>" +value+ "</span>";
			        	}
			        },
			        {field:'remark',title:'简介',width:150,align:'left'},
			        {field:'category',title:'所属类别',width:100,align:'right',
			        	formatter:function(value,row,index){
			        		if(row.category != null && row.category.type != null){
			        			return row.category.type;
			        		}
			        	}
			        }
			    ]]
			});
			//普通文本框 转换为 搜索文本框
			$('#ss').searchbox({ 
				searcher:function(value,name){ 
					//alert(value + "," + name);
					//从服务器加载新数据。如果指定了'param'，它将取代'queryParams'属性
					$('#dg').datagrid('load',{
						name: value //通过input中的name值 重新搜索
					});

					
				}, 
				menu:'#mm', 
				prompt:'Please Input Value' 
			});
		});
	</script>
  </head>
  
  <body>
  	<table id="dg"></table>
  	  
  </body>
</html>

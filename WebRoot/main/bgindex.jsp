<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
    
    <style type="text/css">
		#menu{width:200px;}
		#menu ul{list-style:none;padding:0;margin:0;}
		#menu ul li{border-bottom:1px solid #99CCFF;}
		#menu ul li a{display:block;background-color:#0099CC;color:#fff;padding:5px;}
		#menu ul li a:HOVER {
			background:#00a6ac;
		}
	</style> 
	<script type="text/javascript">
		$(function(){
			$("a[title]").click(function(){//带title属性的a标签
				//alert("111");
				var text = $(this).text();//获取标签中的值
				var href = $(this).attr("title");//链接
				//判断Tabs面板是否有已打开的Tab，如果有，跳转到该Tab
				if($("#tt").tabs("exists",text)){
					$("#tt").tabs("select",text);
				}else{
					//如果没有，新建一个Tab
					$("#tt").tabs("add",{
						title:text,
						closable:true,//关闭按钮
						//href:"send_main_right.action" //链接远程页面，只显示页面的body部分，不显示其他如head部分。所以建议使用content
						content:'<iframe title='+ text +' src='+ href +' frameborder="0" width="100%" height="100%"/>'
					});
				};
			});
		});
	</script>
    
  </head>
  
  <body class="easyui-layout">   
    <div data-options="region:'north',title:'欢迎进入购物频道后台管理页面',split:true" style="height:100px;"></div>   
    <div data-options="region:'west',title:'系统菜单',split:true" style="width:200px;">
    	<!-- 左侧菜单 -->
    	<div id="menu" class="easyui-accordion" data-options="fit:true">   
		    <div title="菜单1">
		  		<ul>
			  		<li><a href="#" title="send_category_query.action">类别管理</a></li>
			  		<li><a href="#" title="send_product_query.action">商品管理</a></li>
		  		</ul>
		  	</div>
		  	<div title="菜单2">
		  		<ul>
			  		<li><a href="#" title="send_sale_sale.action">销售管理</a></li>
			  		<li><a href="#">商品管理</a></li>
		  		</ul>
	  		</div>
		</div>  
		
    </div>   
    <div data-options="region:'center',title:'后台操作页'" style="padding:1px;background:#fff;">
		<div id="tt" class="easyui-tabs" data-options="fit:true">   
		    <div title="系统缺省页" style="padding:10px;display:block;">   
		      	系统缺省页面    
		    </div>   
		</div>
    </div>
    
    <!-- 添加和编辑Category的弹出窗口 : 放在该页面，是为了弹框时锁定整个页面-->
  	<div id="win" data-options="iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,modal:true">
    </div> 
  </body> 
</html>

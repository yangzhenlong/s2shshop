<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
    
    <style type="text/css">
    	form div{margin:40;}
    </style>
    
    <script type="text/javascript">
    	$(function(){
			//添加文本框的验证
			$("input[name='type']").validatebox({
				required:true,
				//当文本框未填写时出现的提示信息
				missingMessage:'请输入类别名称'
			});
			//窗口打开时，默认关闭form表单中所有验证
			$("#ff").form("disableValidation");
			//注册btn事件
			$("#btn").click(function(){
				//开启form验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//调用submit方法，提交数据
					$("#ff").form('submit', {
						url: 'category_save.action',
						success: function(){
							//关闭当前窗体
							parent.$("#win").window("close");
							//刷新数据：跨页面组件获取
							//index.jsp ---> iframe ---> iframe的文档dom --->datagrid --->将jquery对象转换为datagrid对象，调用dg的reload刷新
							parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg").datagrid("reload");
						}
					});
				}
			});
    	});
    </script>

  </head>
  
  <body>
  	<form id="ff" method="post">   
	    <div>   
	        <label for="name">类别名称:</label>   
	        <input type="text" name="type" />   
	    </div>   
	    <div>   
	        	热点<input type="radio" name="hot" value="true" checked="checked" />   
	        	非热点<input type="radio" name="hot" value="false" />   
	    </div>
	    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加类别</a>
	</form>
  </body>
</html>

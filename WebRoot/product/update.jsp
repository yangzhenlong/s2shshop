<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
			
			//下拉列表，远程加载管理员数据
			$('#cc').combobox({
			    url:'account_query.action',    
			    valueField:'id',    
			    textField:'login',
			    panelWidth:'120',
			    width:'120',
			    panelHeight:'auto',
			    editable:false //不许输入文本编辑
			}); 
			
			/*表单中回显数据*/
			//获取datagrid元素
			var dg = parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg");
			var rows = dg.datagrid("getSelections");
			//给表单组件赋值
			$('#ff').form('load',{
				id:rows[0].id,
				type:rows[0].type,
				hot:rows[0].hot,
				'account.id':rows[0].account.id
			});
			//注册btn事件
			$("#btn").click(function(){
				//开启form验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//调用submit方法，提交数据
					$("#ff").form("submit", {
						url:"category_update.action",
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
  		<!-- 隐藏域id -->
        <input type="hidden" name="id"/>   
	    <div>   
	        <label for="name">类别名称:</label>   
	        <input type="text" name="type" />   
	    </div>   
	    <div>   
        	热点<input type="radio" name="hot" value="true" checked="checked" />   
        	非热点<input type="radio" name="hot" value="false" />  
	    </div>
	    <div>   
        	所属管理员
        	<!-- html形式的下拉框
        	<select id="cc" class="easyui-combobox" name="dept" style="width:200px;">   
			    <option value="aa">aitem1</option>   
			    <option>bitem2</option>   
			    <option>bitem3</option>   
			    <option>ditem4</option>   
			    <option>eitem5</option>   
			</select>   -->
			<!-- 远程加载 所属管理员 -->
        	<input id="cc" name="account.id">
	    </div>
	    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">更新类别</a>
	</form>
  </body>
</html>

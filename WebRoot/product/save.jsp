<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 引入公共的JSP片段 -->
	<%@ include file="/public/head.jspf"%>
	<style type="text/css">
		form div{
			margin: 10px;
			font-size: 12;
		}
	</style>
	
	<script type="text/javascript">
		
		$(function(){
			//自定义验证，用户图片格式验证
			$.extend($.fn.validatebox.defaults.rules,{//用于validatebox的自定义验证
				//函数名称upload()
				upload: {
					//函数实现：如果返回false，则验证失败
			        validator: function(value,param){  
			            //alert(value+"--"+param);
			            //获取当前文件后缀名
			            var currExt = value.substring(value.lastIndexOf('.')+1);
			            //获取支持上传的文件后缀名
			            var supportExt = param[0].split(',');
			            for(var i=0;i<supportExt.length;i++){
			            	if(supportExt[i] == currExt){//如果相同，验证成功
			            		return true;
			            	}
			            }
			            return false; 
			        },    
			        message: '文件格式必须为{0}'   
			    }
			});
			
			//下拉列表，远程加载管理员数据
			$('#cc').combobox({
			    url:'category_query.action',    
			    valueField:'id',    
			    textField:'type',
			    panelWidth:'120',
			    width:'120',
			    panelHeight:'auto',
			    editable:false, //不许输入文本编辑
			    required: true,    
			    missingMessage: '请选择所属类别'
			}); 
		
			$("input[name=name]").validatebox({    
			    required: true,    
			    missingMessage: '请输入商品名称'   
			});
			$("input[name=price]").numberbox({    
			    required: true,    
			    min:0,//最小值
    			precision:2, //小数点
    			prefix:'￥',//前缀
    			missingMessage: '请输入商品价格'
			});
			
			//图片上传，调用自定义验证upload
			$("input[name='fileUpload.upload']").validatebox({
				required: true,    
			    missingMessage: '请选择图片',
				validType:"upload['jpg,gif,jpeg,png,tmp']" 
			});
			//当文件域内容发送改变时，调用验证
			$("input[name=upload]").change(function(){
				$(this).validatebox('validate');
			});
			
			//简介验证
			$("textarea[name=remark]").validatebox({    
			    required: true,    
			    missingMessage: '请输入商品简介'   
			});
			
			//窗口打开时，默认关闭form表单中所有验证
			$("#ff").form("disableValidation");
			
			//注册btn事件
			$("#submit").click(function(){
				//开启form验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){
					//调用submit方法，提交数据
					$("#ff").form('submit', {
						url: 'product_save.action',
						success: function(){
							//关闭当前窗体
							parent.$("#win").window("close");
							//刷新数据：跨页面组件获取
							//index.jsp ---> iframe ---> iframe的文档dom --->datagrid --->将jquery对象转换为datagrid对象，调用dg的reload刷新
							parent.$("iframe[title='商品管理']").get(0).contentWindow.$("#dg").datagrid("reload");
						}
					});
				}
			});
			//重置
			$("#reset").click(function(){
				$("#ff").form("reset");
			});
		});
	</script>
</head>

<body>
<form title="添加商品" id="ff" method="post" enctype="multipart/form-data">
	<div>
		<label>商品名称:</label> <input type="text" name="name" />
	</div>
	<div>
		<label>商品价格:</label> <input type="text" name="price" />
	</div>
	<div>
		<label>图片上传:</label> <input type="file" name="fileUpload.upload" />
	</div>
	<div>
		<label>所属类别：</label> 
		<input id="cc" name="category.id"/>
	</div>
	<div>
		<label>加入推荐:</label> 推荐:<input type="radio" name="commend"
			checked="checked" value="true" />  不推荐:<input type="radio"
			name="commend" value="false" /> 
	</div>
	<div>
		<label>是否有效:</label>
		上架:<input type="radio" name="open" checked="checked"value="true" />
		下架:<input type="radio" name="open" value="false" />
				
	</div>
	<div>
		<label>简单描述:</label>
		<textarea name="remark" cols="40" rows="3"></textarea>
	</div>
	<div>
		<label>详细描述:</label>
		<textarea name="xremark" cols="40" rows="6"></textarea>
	</div>
	<div>
		<a id="submit" href="#" class="easyui-linkbutton">添 加</a> 
		<a id="reset" href="#" class="easyui-linkbutton">重 置</a>
	</div>
</form>
</body>
</html>

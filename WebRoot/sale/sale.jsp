<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
    <style type="text/css">
    	select{margin-right:30px;}
    </style>
	<script type="text/javascript" src="${shop}/jquery-easyui-1.3.5/jscharts.js"></script>
	<script type="text/javascript">
		//颜色数组
		var colorArr = ['#5C447A','#6C469D','#7B36D1','#8939ED','#964FEF','#B17BF4','#C9A3F7','#5C447A','#6C469D','#7B36D1'];
		//页面加载是调用
		$(function(){
			$("#submit").click(function(){
				//发送ajax请求
				$.post($("#sale").val(), {number:$("#number").val()}, function(data){
					//alert(data);
					//展示报表
					var myChart = new JSChart('chart_container', $("#type").val(), data);
						myChart.setDataArray(data);
						//myChart.colorize(['#5C447A','#6C469D','#7B36D1','#8939ED','#964FEF','#B17BF4','#C9A3F7']);
						myChart.colorize(colorArr.slice(0, data.length));//截取颜色数组的数据
						myChart.setSize(100*$("#number").val(), 300);
						myChart.setIntervalEndY(200);
						myChart.setTitle('商品销售报表');
						myChart.setTitleFontSize(10);
						myChart.setBarSpacingRatio(50);
						myChart.setBarOpacity(1);
						myChart.setAxisWidth(1);
						myChart.setAxisNameX('商品名称', true);
						myChart.setAxisNameY('销售量', false);
						myChart.set3D(true);
						myChart.setBarValues(false);
						myChart.setAxisValuesPaddingLeft(50);
						myChart.setAxisPaddingBottom(40);
						myChart.setAxisValuesColorX('#FF00CC');
						myChart.setAxisNameFontSizeX(12);
						myChart.setAxisNameFontSizeY(12);
						//myChart.setTitleColor('#0000FF');
						myChart.setTitleFontSize(16);
						myChart.setAxisValuesColorY('#FF0000');
						myChart.draw();
				},"json");
			});
		});
	
	
		
	</script>  	
  </head>
  
	<body>
		<div style="padding:20px;">
			销售类型：
			<select id="sale">
				<option value="sorder_querySale.action">年度销售报表</option>
			</select>
			查询数量：
			<select id="number">
				<option value="5">5</option>
				<option value="7">7</option>
				<option value="10">10</option>
			</select>
			报表类型：
			<select id="type">
				<option value="bar">柱状图</option>
				<option value="line">线形图</option>
				<option value="pie">饼图</option>
			</select>
			<input type="submit" style="width:80px;" id="submit" value="查询" />
		</div>
		
		<hr style="margin:20px 5px 20px 5px;"/>
		
		<div id="chart_container" style="float:left;margin-left:50px;">Loading chart...</div>
		
	</body>
</html>

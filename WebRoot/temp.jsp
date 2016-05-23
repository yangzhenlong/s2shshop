<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>

	<style type="text/css">
		#menu{width:200px;}
		#menu ul{list-style:none;padding:0;margin:0;}
		#menu ul li{border-bottom:1px solid red;}
		#menu ul li a{display:block;background:#008792;padding:5px;}
		#menu ul li a:HOVER {
			background:#00a6ac;
		}
	</style>    
  </head>
  
  <body>
  	<div id="menu">
	  	<div title="菜单1">
	  		<ul>
		  		<li><a href="#">类别管理</a></li>
		  		<li><a href="#">商品管理</a></li>
	  		</ul>
	  	</div>
	  	<div title="菜单1">
	  		<ul>
		  		<li><a href="#">类别管理</a></li>
		  		<li><a href="#">商品管理</a></li>
	  		</ul>
	  	</div>
  	</div>
  </body>
</html>

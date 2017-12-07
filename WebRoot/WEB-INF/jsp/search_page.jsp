<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<rapid:override name="static_pre">
	<link href="styles/search_page.css" rel="stylesheet">
</rapid:override>

<rapid:override name="content">
	<div class="col-sm-12 col-xs-12">
		<div class="row">
			<div id="logo" class="col-sm-4 col-sm-offset-4">
				<img width="120" src="img/logo.png" alt="logo">
			</div>
		</div>
		<form role="form" action="search_results" method="post"
			class="search_form">

			<div class="form-group row" id="search-group">
				<div class="col-sm-5 col-xs-10 col-sm-offset-3 col-xs-offset-1" id="div_input">
					<input type="text" name="search_text" class="form-control"
						id="searchtext" required pattern="[^\\,;]+"
						oninvalid="setCustomValidity('搜索内容不能为空，且不包含特殊字符(\\，；)。')"
						oninput="setCustomValidity('')"> <input type="hidden"
						name="search_category" id="search_category" value="category">
					<input type="hidden" name="page_index" value="1">
				</div>
				<div class="col-sm-1 col-xs-10 col-sm-offset-0 col-xs-offset-1" id="div_btn">
					<button type="submit" class="btn btn-primary">搜索</button>
				</div>
			</div>
		</form>

		<div class="col-sm-6 col-sm-offset-3" id="recommendation">
			<div class="row">
				<c:forEach items="${articles_former}" var="article" varStatus="st">
					<a href="${article.url}" class="col-sm-3 col-xs-6" title="${article.abstract}">${article.title}</a> 
				</c:forEach>
			</div>
			<div class="row">
				<c:forEach items="${articles_latter}" var="article" varStatus="st">
					<a href="${article.url}" class="col-sm-3 col-xs-6" title="${article.abstract}">${article.title}</a> 
				</c:forEach>
			</div>
		</div>
	</div>
</rapid:override>

<jsp:include page="./home.jsp"></jsp:include>

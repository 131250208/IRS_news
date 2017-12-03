<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>

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
				<a href="#" class="col-sm-3 col-xs-6">推荐热门新闻1</a> 
				<a href="#" class="col-sm-3 col-xs-6">推荐热门新闻2</a> 
				<a href="#" class="col-sm-3 col-xs-6">推荐热门新闻3</a>
				<a href="#" class="col-sm-3 col-xs-6">推荐热门新闻4</a>
			</div>
			<div class="row">
				<a href="#" class="col-sm-3 col-xs-6">推荐热门新闻5</a> 
				<a href="#" class="col-sm-3 col-xs-6">推荐热门新闻6</a> 
				<a href="#" class="col-sm-3 col-xs-6">推荐热门新闻7</a>
				<a href="#" class="col-sm-3 col-xs-6">推荐热门新闻8</a>
			</div>
		</div>
	</div>
</rapid:override>

<jsp:include page="./home.jsp"></jsp:include>

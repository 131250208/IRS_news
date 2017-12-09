<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<rapid:override name="static_pre">
	<link href="styles/search_results.css" rel="stylesheet">
</rapid:override>

<rapid:override name="content">
	<!-- 搜索框 -->
	<div id="search_form" role="form">
		<div class="form-group row" id="search-group">
			<div class="col-sm-5" id="div_input">
				<input type="text" id="searchtext" name="search_text" value="${search_text}"
					class="form-control" pattern="[^\\,;]+" required
					oninvalid="setCustomValidity('搜索内容不能为空，且不包含特殊字符(\\，；)。')"
					oninput="setCustomValidity('')"> 
				<input type="hidden"
					name="input_page_index" value="${page_index}"> 
				<input
					type="hidden" name="input_page_total" value="${page_total}">
			</div>
			<div class="col-sm-1" id="div_btn">
				<button type="submit" id="btn_search"
					class="btn btn-sm btn-primary btn-block">搜索</button>
			</div>
		</div>
	</div>

	<!-- 排序依据 -->
	<ul class="nav nav-tabs" id="nav_result_cat">
		<li class="cat" id="cat_category"><a data-toggle="tab">相关度</a></li>
		<li class="cat" id="cat_type"><a data-toggle="tab">时间</a></li>
		<li class="cat" id="cat_brand"><a data-toggle="tab">热度</a></li>
	</ul>


	<div id="content-left" class="col-sm-7">
		<!-- 相关查询推荐（词项） -->
		<div id="sim_words">
			相关查询推荐:
			<c:forEach items="${list_sim_words}" var="word" varStatus="st">
				<a href="#">${word}</a>
			</c:forEach>
		</div>

		<!-- 查询结果条数 -->
		<p id="count_results">天眼为您提供相关结果：1000条</p>

		<!-- 查询相关词项推荐 -->

		<!-- 查询结果 -->
		<c:forEach items="${list_news}" var="news" varStatus="st">
			<div class="result row">
				<h4 class="title ">
					<a href="${news.url}" target="_blank">${news.title}</a> <span
						class="count_view glyphicon glyphicon-eye-open">（${news.heat}）</span>
				</h4>
				<div class="abstract col-sm-12 col-xs-12">
					<span class="date"> ${news.date}&nbsp;-&nbsp; </span>
					${news.abstract_}
				</div>
				<div class="bottom col-sm-12 col-xs-12">
					<a href="#" target="_blank" id="comments_up" data-news-id="${news.id}"><span
						class="glyphicon glyphicon-thumbs-up"></span>好评(121)</a> 
					<a href="#" target="_blank" id="comments_down" data-news-id="${news.id}"><span class="glyphicon glyphicon-thumbs-down"></span>差评(3)</a>

					<a target="_blank" href="#?id=${news.id}">相似新闻&nbsp;>>&nbsp;</a>
				</div>
			</div>
		</c:forEach>

		<!-- 分页组件 -->
		<div style="text-align: center">
			<ul class="pagination"></ul>
		</div>
	</div>

	<!-- 相关文章推荐（相关词项的第一二篇以及该查询第一篇的相关文档） -->
	<div id="content-right" class="col-sm-5"></div>
</rapid:override>

<rapid:override name="static_after">
	<script src="scripts/pagination.js"></script>
	<script src="scripts/search_results.js"></script>
</rapid:override>
<jsp:include page="./home.jsp"></jsp:include>

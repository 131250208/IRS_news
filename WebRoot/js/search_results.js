/**
 * 
 */

// 点击页码后会调用的函数
function after_click_page() {
    //var page_index = $("input[name= 'page_index']").val();
    $("form#search_form").submit();
}
// 折叠面板的点击事件函数
function collapse_listener(){
	$("a.comments_up").bind("click",function(){
		$(this).parent().next().find("div.collapse").collapse("hide");
		$(this).parent().next().find("div.comments_up").collapse("toggle");
	});
	$("a.comments_down").bind("click",function(){
		$(this).parent().next().find("div.collapse").collapse("hide");
		$(this).parent().next().find("div.comments_down").collapse("toggle");
	});
	$("a.sim_news").bind("click",function(){
		$(this).parent().next().find("div.collapse").collapse("hide");
		$(this).parent().next().find("div.news_sim").collapse("toggle");
	});
}
// 推荐词项的点击事件函数
function recmmended_words_listener(){
	$("a.recommended_word").bind("click",function(){
		var word = $(this).data("word");
		$("input[name = 'search_text']").val(word);
		$("form#search_form").submit();
	});
}

// 搜索类型tab的点击事件
function search_type_tabs_listener(){
	$("ul#nav_search_type > li > a").bind("click",function(){
		var type = $(this).data("type");
		$("input[name = 'ranking_indicator']").val(type);
		$("form#search_form").submit();
		
	});
}
$(document).ready(
    function () {
    	// 激活标签
    	var ranking_indicator = $("input[name= 'ranking_indicator']").val();
		$("ul#nav_search_type > li > a[data-type = '"+ ranking_indicator +"']").parent().addClass("active");
		
    			
        var input_page_total = $("input[name= 'page_total']");
        var input_page_index = $("input[name= 'page_index']");

        // 按当前页码和页码总数调整页码组件,并传入点击页码后需要调用的函数
        adjust_pages(input_page_total, input_page_index, after_click_page);
        
        // 给折叠区域加监听点击的事件
        collapse_listener();
        
        // 给推荐的关键词添加点击事件->提交查询
        recmmended_words_listener();
        
        // 给搜索类型指标tab添加点击事件->提交查询
        search_type_tabs_listener();
    }
);
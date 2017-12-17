/**
 * 
 */

// 点击页码后会调用的函数
function after_click_page() {
    var page_index = $("input[name= 'input_page_index']").val();
    //get_manage_logs(page_index);
}
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
$(document).ready(
    function () {
        var input_page_total = $("input[name= 'input_page_total']");
        var input_page_index = $("input[name= 'input_page_index']");

        // 按当前页码和页码总数调整页码组件,并传入点击页码后需要调用的函数
        adjust_pages(input_page_total, input_page_index, after_click_page);
        
        // 给折叠区域加监听点击的事件
        collapse_listener();
    }
);
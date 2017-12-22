/**
 * 
 */

$(document).ready(function(){
	$.get("all_words",function(data,status){
	    if(status == "success"){
	    	var words = JSON.parse(data);
	    	$("input#searchtext").typeahead({source: words});
	    }
	  });
});


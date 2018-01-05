
$(document).ready(function(){
	$.get("get_words",function(data,status){
	    if(status == "success"){
	    	$("input#searchtext").typeahead({source: data});
	    }
	  }, 'json');
});


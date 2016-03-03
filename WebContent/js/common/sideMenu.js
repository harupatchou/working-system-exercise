$(document).ready(function(){

});

$(document).on("click",".sideButton",function(){
	$("#sideform").prop("action",$(this).attr("name"));
});


$(document).ready(function(){

});

/** サイドメニューから飛ぶSerbletの情報を取得 */
$(document).on("click",".sideButton",function(){
	$("#sideform").prop("action",$(this).attr("id"));
});


$(document).ready(function(){
  var tempData = $("#laborSystem").val();
  var tempUrl = "return openWin('/working/window/attendanceTime?laborSystemId="+tempData+"')";

  $("#windowButton").attr('onclick',tempUrl);
});

//セレクト変更時、onclickのURLを変更する
$(function(){
	$(document).on("change","#laborSystem",function(){
		var tempData = $("#laborSystem").val();
		var tempUrl = "return openWin('/working/window/attendanceTime?laborSystemId="+tempData+"')";

		$("#windowButton").attr('onclick',tempUrl);
	});
})

//小窓
var w = window;
function openWin(url) {
	if ((w == window) || w.closed) {
		w = open(url, "_blank", "width=1200,height=600");
	} else {
		w.focus();
	}
	return(false);
}

//子から親へ
function closeWindow() {
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();

	window.opener.document.companyEdit.hiddenStartTime.value = startTime;
	window.opener.document.companyEdit.hiddenEndTime.value = endTime;

	window.opener.document.getElementById("startTime").innerHTML=startTime;
	window.opener.document.getElementById("endTime").innerHTML=endTime;

	window.close();
}
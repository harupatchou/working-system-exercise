/** 月次勤怠画面 */
$(document).ready(function(){
	$(".month_val").each(function(){
		if ($(".selectMonth").val() == $(this).val()){
			$(this).prop("selected",true);
		}
	});
});

$(document).on("change",".changeMonth",function(){
	document.monthlyAttendance.action="/kot/employee/MonthlyAttendance";
	document.monthlyAttendance.submit();
});
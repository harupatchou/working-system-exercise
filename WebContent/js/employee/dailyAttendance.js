$(document).ready(function(){
  var checkInf = $(".attendance_day").val().split("/");

  $("#yearHidden").val(checkInf[0]);
  $("#monthHidden").val(checkInf[1]);
  $("#dayHidden").val(checkInf[2]);

});
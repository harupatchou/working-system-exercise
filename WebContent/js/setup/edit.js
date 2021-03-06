/** 従業員登録 */
$(function(){
  /* フォーカス */
  $("input").focus(function(){
    $(this).css("background","#b3eaef");
  }).blur(function(){
    $(this).css("background","#ffffff");
  });

  /* パスワードチェック */
  $(".password_conf").focus(function(){
  }).blur(function(){
    var checkInf = $(".password_info").val();
    if(checkInf == $(this).val()){
        return
    }else{
        alert("パスワードが一致しません!");
    }
  });

/** 出退勤画面 */
  /* 出退勤形式チェック */
  $(".attendance_time").focus(function(){
  }).blur(function(){
    var arrayOfStrings = $(this).val().split(":");
    var checkInf = arrayOfStrings.length;
    if(checkInf == 2){
        $(".insert_button").prop("disabled",false)


        return
    }else{
    	$(".insert_button").prop("disabled",true)
        alert("00:00の形式で入力してください。");
    }
  });

  $(".attendance_day").focus(function(){
  }).blur(function(){
    var checkInf = $(this).val().split("/");
    var strings = "";
    if(checkInf.length == 3){
        for(i=0; i<checkInf.length; i++){
            strings += checkInf[i];
        }
        if(strings.length == 8){
            $(".insert_button").prop("disabled",false)

            $("#yearHidden").val(checkInf[0]);
            $("#monthHidden").val(checkInf[1]);
            $("#dayHidden").val(checkInf[2]);

        }else{
             alert("0000/00/00の形式で入力してください。");
        }
        return
    }else{
    	$(".insert_button").prop("disabled",true)
        alert("/区切りの形式で入力してください。");
    }
  });

  //セレクト変更時、表示する内容を変更する
  $(function(){
  	$(document).on("change","#laborSystem",function(){
  		var selectNum = $(this).val();

  		var strStartTime = $("#selectStart_"+selectNum).val();
  		var strEndTime = $("#selectEnd_"+selectNum).val();

  		$("#hiddenStartTime").val(strStartTime);
  		$("#hiddenEndTime").val(strEndTime);

  		$("#startTime").text(strStartTime);
  		$("#endTime").text(strEndTime);

  		if($(this).val() == 2){
  			$("#irregularArea").show();
  		}else{
  			$("#irregularArea").hide();
  		}
  	});
  })

  $(document).ready(function(){
	var selectNum = $("#laborSystem").val();

	var strStartTime = $("#selectStart_"+selectNum).val();
	var strEndTime = $("#selectEnd_"+selectNum).val();

	$("#hiddenStartTime").val(strStartTime);
	$("#hiddenEndTime").val(strEndTime);

	$("#startTime").text(strStartTime);
	$("#endTime").text(strEndTime);
  });


});
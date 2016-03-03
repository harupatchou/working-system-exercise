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


});
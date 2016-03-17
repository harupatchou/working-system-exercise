<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="../js/setup/window.js"></script>
<title>Insert title here</title>
</head>
<body>
<h1>勤務時間編集</h1>
<div>
<label>出勤時間：</label>
  <input type="text" id="startTime" name="attendanceTime" value="${startTime}">
</div>
<div>
<label>退社時間：</label>
  <input type="text" id="endTime" name="leaveTime" value="${endTime}">
</div>
<input type="button" value="保存" onclick="closeWindow()">
</body>
</html>
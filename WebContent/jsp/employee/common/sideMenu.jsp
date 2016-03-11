<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="/kot/employee" method="GET" id="sideform">
  <input type="hidden" name="employeeId" value="1">
  <ul>
    <li>
      <input type="submit" value="パスワード変更" class="sideButton" id="PasswordEdit">
    </li>
    <li>
      <input type="submit" value="月次申請" class="sideButton" id="MonthlyEdit">
    </li>
    <li>
      <input type="submit" value="日次申請" class="sideButton" id="Attendance">
    </li>
  </ul>
</form>

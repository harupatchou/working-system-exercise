<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/employee/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <h4 class="subTitle">出退勤入力</h4>
    <form action="/kot/employee/Attendance" method="POST">
      <div class="patternA">
        <ul>
          <li>
            <label>日付選択：</label>
            <div>
              <input type="text" placeholder="0000/00/00" class="attendance_day">
<!-- hidden領域  -->
              <input type="hidden" name="year" id="yearHidden">
              <input type="hidden" name="month" id="monthHidden">
              <input type="hidden" name="day" id="dayHidden">
<!-- ここまで  -->
            </div>
          </li>
          <li><hr><br></li>
          <li>
            <label>出勤：</label>
            <div>
              <input type="text" placeholder="00:00" class="attendance_time">
            </div>
          </li>
          <li>
            <label>退勤：</label>
            <div>
              <input type="text" placeholder="00:00" class="attendance_time">
            </div>
          </li>
          <li>
            <label>休憩開始：</label>
            <div>
              <input type="text" placeholder="00">
            </div>
          </li>
          <li>
            <label>休憩終了：</label>
            <div>
              <input type="text" placeholder="00">
            </div>
          </li>
        </ul>
        <div class="saveButton">
          <input type="submit" value="登録" class="insert_button">
          <!-- TODO 決め打ち  -->
          <input type="hidden" name="employeeId" value="1">
        </div>
      </div>
    </form>

<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
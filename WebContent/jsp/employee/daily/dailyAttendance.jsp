<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/employee/common/base.jsp">
  <c:param name="content">
<script src="../js/employee/dailyAttendance.js"></script>
<%--ここから下にコンテンツを挿入 --%>
    <h4 class="subTitle">出退勤入力</h4>
    <form action="/kot/employee/Attendance" method="POST">
      <div class="patternA">
        <ul>
          <li>
            <label>日付選択：</label>
            <div>
              <input type="text" placeholder="0000/00/00" class="attendance_day" value="${selectDay}">
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
              <input type="text" placeholder="00:00" class="attendance_time" name="startTime" value="${workingDay.attendanceTime}">
              <input type="hidden" name="hourStart" class="attendance_time_hidden">
              <input type="hidden" name="minuteStart" class="attendance_time_hidden">
            </div>
          </li>
          <li>
            <label>退勤：</label>
            <div>
              <input type="text" placeholder="00:00" class="attendance_time" name="endTime" value="${workingDay.leaveTime}">
              <input type="hidden" name="hourEnd" class="attendance_time_hidden">
              <input type="hidden" name="minuteEnd" class="attendance_time_hidden">
            </div>
          </li>
          <li>
            <label>休憩開始：</label>
            <div>
              <input type="text" placeholder="00:00" class="attendance_time" name="breakStartTime" value="${workingDay.breakTimeStart}">
              <input type="hidden" name="hourBreakStart" class="attendance_time_hidden">
              <input type="hidden" name="minuteBreakStart" class="attendance_time_hidden">
            </div>
          </li>
          <li>
            <label>休憩終了：</label>
            <div>
              <input type="text" placeholder="00:00" class="attendance_time" name="breakEndTime" value="${workingDay.breakTimeEnd}">
              <input type="hidden" name="hourBreakEnd" class="attendance_time_hidden">
              <input type="hidden" name="minuteBreakEnd" class="attendance_time_hidden">
            </div>
          </li>
          <li>
            <label>出欠：</label>
            <div>
              <select name="attend_status">
                <c:forEach items="${attendanceStatus}" var="status" varStatus="i">
                  <option value="${i.index+1}">${status.statusName}</option>
                </c:forEach>
              </select>
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
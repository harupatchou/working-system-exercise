<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/employee/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <h2 class="subTitle">月次勤怠</h2>
    <!-- TODO 後ほど修正 -->
    <p class="emp_info">従業員名：test</p>
    <form name="monthlyAttendance" method="GET">
      <p>年月選択：
      <select name="year">
        <c:forEach items="${yearList}" var="year">
          <option value="${year}" class="year_val">${year}</option>
        </c:forEach>
      </select>
      <input type="hidden" name="selectYear" class="selectYear" value="${selectYear}">
      <select name="month" class="changeMonth" >
        <c:forEach var="month" varStatus="i" begin="1" end="12" step="1">
          <option value="${i.index}" class="month_val">${i.index}</option>
        </c:forEach>
      </select>
      <input type="hidden" name="selectMonth" class="selectMonth" value="${selectMonth}">
    </form>
    <form>
      <h4 class="subTitle">${selectMonth}月の勤怠</h4>
      <div class="a_pattern_table">
        <table class="calendar_table">
          <thead>
            <tr>
              <th>日付</th>
              <th>曜日</th>
              <th>種別</th>
              <th>状態</th>
              <th>出欠</th>
              <th>開始</th>
              <th>終了</th>
              <th>休憩</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach varStatus="i" items="${scheduleList}" var="schedule">
              <c:choose>
                <c:when test="${schedule.weekStr.equals('日')}"><tr class="schedule-sunday"></c:when>
                <c:when test="${schedule.weekStr.equals('土')}"><tr class="schedule-saturday"></c:when>
                <c:otherwise><tr class="schedule-normal"></c:otherwise>
              </c:choose>
                <td class="day"><a href="/kot/employee/Attendance?day_num=${schedule.monthlyDate}">${schedule.monthlyDate}</a></td>
                <td class="week">${schedule.weekStr}</td>
                <td>${schedule.strHoliday}</td>
                <td>${schedule.enterStatus}</td>
                <td>${schedule.workingDay.attendanceStatus}</td>
                <td>${schedule.workingDay.attendanceTime}</td>
                <td>${schedule.workingDay.leaveTime}</td>
                <td>${schedule.workingDay.napTime}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </form>
<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
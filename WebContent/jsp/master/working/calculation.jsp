<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/master/common/base.jsp">
  <c:param name="content">
    <h4 class="subTitle">計算結果</h4>
      <div class="a_pattern_table">

        表示期間：${date.year} 年 ${date.month}月

        <table class="normal_table">
          <thead>
            <tr>
              <th>従業員名</th>
              <th>労働種別</th>
              <th>総労働時間</th>
              <th>法定内残業時間</th>
              <th>法定外残業時間</th>
              <th>深夜</th>
              <th>深夜残業</th>
              <th>法定内休日</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="workingTimeTotal" items="${workingTimeTotalList}">
            <tr>
              <td>${workingTimeTotal.employee.firstName} ${workingTimeTotal.employee.lastName}</td>
              <td>${workingTimeTotal.workingtype.workingName}</td>
              <td>${workingTimeTotal.workingTimeTotal}</td>
              <td>${workingTimeTotal.legalOverWorkingTimeTotal}</td>
              <td>${workingTimeTotal.statutoryOverWorkingTimeTotal}</td>
              <td>${workingTimeTotal.nightTimeTotal}</td>
              <td>${workingTimeTotal.overNightTimeTotal}</td>
              <td>${workingTimeTotal.holidayTimeTotal}</td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
  </c:param>
</c:import>
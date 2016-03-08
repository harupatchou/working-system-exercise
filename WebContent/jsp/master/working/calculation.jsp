<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/master/common/base.jsp">
  <c:param name="content">
    <h4 class="subTitle">計算結果</h4>
      <div class="a_pattern_table">

        表示期間：${workingTimeTotal.year} 年 ${workingTimeTotal.month}月

        <table class="normal_table">
          <thead>
            <tr>
              <th rowspan="2">従業員名</th>
              <th rowspan="2">労働種別</th>
              <th>総労働時間</th>
              <th>法定内残業時間</th>
              <th>法定外残業時間</th>
              <th>深夜</th>
              <th>深夜残業</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>${employee.firstName} ${employee.lastName}</td>
              <td>${workingtype.workingName}</td>
              <td>${workingTimeTotal.workingTimeTotal}</td>
              <td>${workingTimeTotal.legalOverWorkingTimeTotal}</td>
              <td>${workingTimeTotal.nonLegalOverWorkingTimeTotal}</td>
              <td>${workingTimeTotal.nightTimeTotal}</td>
              <td>${workingTimeTotal.overNightTimeTotal}</td>
            </tr>
          </tbody>
        </table>
      </div>
  </c:param>
</c:import>
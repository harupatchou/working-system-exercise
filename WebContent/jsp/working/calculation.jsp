<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/common/base.jsp">
  <c:param name="content">
    <h4 class="subTitle">計算結果</h4>
      <div class="a_pattern_table">
        <table class="normal_table">
          <thead>
            <tr>
              <th>従業員名</th>
              <th>従業員種別</th>
              <th>総労働時間</th>
              <th>総残業時間</th>
              <th>深夜</th>
              <th>深夜残業</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>test</td>
              <td>a</td>
              <td>b</td>
              <td>c</td>
              <td>d</td>
              <td>e</td>
            </tr>
          </tbody>
        </table>
      </div>
    ${workingTimeAll.workingTimeAll}
  </c:param>
</c:import>
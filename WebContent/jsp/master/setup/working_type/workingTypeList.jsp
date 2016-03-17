<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/jsp/master/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <form action="/kot/master/EmployeeEdit" method="POST">
      <h1>従業員一覧</h1>
      <div class="a_pattern_table">
        <table class="normal_table">
          <thead>
            <tr>
              <th>編集</th>
              <th>従業員ID</th>
              <th>従業員名</th>
              <th>従業員種別</th>
            </tr>
          </thead>
            <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
          <tbody>
          </tbody>
        </table>
      </div>
    </form>
<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
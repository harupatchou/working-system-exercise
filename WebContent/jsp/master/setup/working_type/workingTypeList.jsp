<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/jsp/master/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <form action="/working/master/EmployeeEdit" method="POST">
      <h1>従業員種別一覧</h1>
      <div class="a_pattern_table">
        <table class="normal_table">
          <thead>
            <tr>
              <th>編集</th>
              <th>従業員種別ID</th>
              <th>従業員種別名</th>
              <th>労働制</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="workingtype" items="${workingtypeList}">
            <tr>
              <td>
                <input type="button" value="編集">
                <input type="button" value="削除">
              </td>
              <td>${workingtype.id}</td>
              <td>${workingtype.workingName}</td>
              <td>${workingtype.laborSystem.laborSystemName}</td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </form>
<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
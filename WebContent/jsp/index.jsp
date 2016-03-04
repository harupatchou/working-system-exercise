<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>

    <h4 class="subTitle">労働計算</h4>
    <form action="/kot/Calculation" method="POST">
      <input type="submit" value="計算">
      <input type="hidden" name="employeeId" value="1">
      <input type="hidden" name="year" value="2015">
      <input type="hidden" name="month" value="12">
    </form>

<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
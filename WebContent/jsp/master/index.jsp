<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/master/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>

    <h4 class="subTitle">労働計算</h4>
    <form action="/kot/master/Calculation" method="POST">
      <select name="year">
        <c:forEach var="year" items="${selectYear}">
          <option value="${year}">${year}</option>
        </c:forEach>
      </select>
      <select name="month">
        <c:forEach var="month" items="${selectMonth}">
          <option value="${month}">${month}</option>
        </c:forEach>
      </select>
      <input type="submit" value="計算">
    </form>

<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
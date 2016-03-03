<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <h4 class="subTitle">労働計算</h4>
    <form action="/kot/Calculation" method="POST">
      <input type="submit" name="employee" value="計算">
    </form>
<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
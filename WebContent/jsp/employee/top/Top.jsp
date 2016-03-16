<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/employee/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>

    <h4 class="subTitle">可能残業時間</h4>
    <p>${overtimeMessage}</p>


<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
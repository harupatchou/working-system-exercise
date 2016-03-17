<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/master/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <form action="/kot/master/CompanyEdit" method="POST">
      <h1>会社情報編集</h1>
      <span class="warnA">*</span>は必須項目
      <div class="patternA">
        <h2>企業情報</h2>
        <ul>
          <li>
            <label>会社名<span class="warnA">*</span>：</label>
            <div>
              <input type="text" name="companyName" value="${userCompany.companyName}">
            </div>
          </li>
          <li>
            <label>会社ID<span class="warnA">*</span>：</label>
            <div>
              <input type="text" name="companyId" value="${userCompany.id}">
            </div>
          </li>
          <li><br><hr><br></li>
        </ul>
        <h2>勤怠情報</h2>
        <ul>
          <li>
            <label>勤務時間編集：</label>
            <div>
              <select name="laborSystemId">
                <c:forEach items="${attendanceTimeList}" var="attend">
                  <option value="${attend.laborSystemId}">${attend.laborSystem.laborSystemName}</option>
                </c:forEach>
              </select>
              <input type="button" value="編集" onClick="return openWin('/kot/window/attendanceTime')">
            </div>
          </li>
        </ul>
      </div>
      <div class="saveButton">
        <input type="submit" value="登録">
      </div>
    </form>
<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
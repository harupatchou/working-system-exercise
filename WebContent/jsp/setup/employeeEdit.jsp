<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/jsp/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <form action="/kot/EmployeeEdit" method="POST">
      <h1>従業員情報編集</h1>
      <span class="warnA">*</span>は必須項目
      <div class="patternA">
        <ul>
          <li>
            <label>姓・名<span class="warnA">*</span>：</label>
            <div>
              <input type="text" name="lastName">
              <input type="text" name="firstName">
            </div>
          </li>
          <li>
            <label>従業員ID<span class="warnA">*</span>：</label>
            <div>
              <input type="text" name="employeeId">
            </div>
          </li>
          <li>
            <label>所属会社：</label>
            <div>
              <input type="button" value="編集">
            </div>
          </li>
          <li>
            <label>従業員種別：</label>
            <div>
              <input type="button" value="編集">
            </div>
          </li>
          <li><hr><br></li>
          <li>
            <label>パスワード<span class="warnA">*</span>：</label>
            <div>
              <input type="password" name="password" class="password_info">
            </div>
          </li>
          <li>
            <label>パスワード(確認用)<span class="warnA">*</span>：</label>
            <div>
              <input type="text" name="confirmation" class="password_conf">
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
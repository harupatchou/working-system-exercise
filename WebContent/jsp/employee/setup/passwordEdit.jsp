<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/employee/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>

    <form action="/kot/employee/PasswordEdit" method="POST">
      <h1>パスワード編集</h1>
      <p>${message}</p>
      <div class="patternA">
        <ul>
          <li>
            <label>現在のパスワード：</label>
            <div>
              <input type="text" name="password">
            </div>
          </li>
          <li>
            <label>新規パスワード：</label>
            <div>
              <input type="text" name="new_password">
            </div>
          <li>
            <label>新規パスワード確認：</label>
            <div>
              <input type="password" name="confirm_new_password">
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
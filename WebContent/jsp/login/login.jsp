<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/login/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <form action="/working/login" method="POST">
      <h1>ログインページ</h1>
      <div class="patternA">
        <ul>
          <li>
            <label>ID：</label>
            <div>
              <input type="text" name="userId">
            </div>
          </li>
          <li>
            <label>パスワード：</label>
            <div>
              <input type="password" name="password">
            </div>
          </li>
        </ul>
      </div>
      <div class="saveButton">
        <input type="submit" value="ログイン">
      </div>
    </form>
<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
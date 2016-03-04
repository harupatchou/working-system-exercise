<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <form action="/kot/CompanyEdit" method="POST">
      <h1>会社情報編集</h1>
      <span class="warnA">*</span>は必須項目
      <div class="patternA">
        <ul>
          <li>
            <label>会社名<span class="warnA">*</span>：</label>
            <div>
              <input type="text" name="companyName">
            </div>
          </li>
          <li>
            <label>会社ID<span class="warnA">*</span>：</label>
            <div>
              <input type="text" name="companyId">
            </div>
          </li>
          <li>
            <label>所属従業員種別：</label>
            <div>
              <input type="button" value="編集">
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
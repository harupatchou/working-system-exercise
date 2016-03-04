<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <form action="/kot/WorkingtypeEdit" method="POST">
      <h1>従業員種別情報編集</h1>
      <span class="warnA">*</span>は必須項目
      <div class="patternA">
        <ul>
          <li>
            <label>従業員種別名<span class="warnA">*</span>：</label>
            <div>
              <input type="text" name="workingName">
            </div>
          </li>
          <li>
            <label>従業員種別ID<span class="warnA">*</span>：</label>
            <div>
              <input type="text" name="workingtypeId">
            </div>
          </li>
          <li>
            <label>種別基準時間：</label>
            <div>
              <input type="text" name="workingTime">
              <span class="infomation">※フレックスの場合は「f」と入力してください</span>
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
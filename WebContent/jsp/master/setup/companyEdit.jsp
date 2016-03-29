<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/master/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>
    <form name="companyEdit" action="/kot/master/CompanyEdit" method="POST">
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
              <c:if test="${attendanceTimeList.size() != 0}">
                <div>
                  <select name="laborSystemId" id="laborSystem">
                    <c:forEach items="${attendanceTimeList}" var="attend">
                      <option value="${attend.laborSystem.id}">${attend.laborSystem.laborSystemName}</option>
                   </c:forEach>
                  </select>
                  <c:forEach items="${attendanceTimeList}" var="attend" varStatus="i">
                    <input type="hidden" id="selectStart_${attend.laborSystem.id}" value="${attendanceTimeList.get(i.index).getStartTime()}">
                    <input type="hidden" id="selectEnd_${attend.laborSystem.id}" value="${attendanceTimeList.get(i.index).getEndTime()}">
                  </c:forEach>
                  <input type="button" value="編集" id="windowButton">
                </div>
                <section>
                  <div>
                    出勤時間：<label id="startTime">${attendanceTimeList.get(0).getStartTime()}</label>
                  </div>
                  <div>
                    退勤時間：<label id="endTime">${attendanceTimeList.get(0).getEndTime()}</label>
                  </div>
                  <input type="hidden" name="attendanceTime" id="hiddenStartTime" value="">
                  <input type="hidden" name="leaveTime" id="hiddenEndTime" value="">
                </section>
              </c:if>
              <c:if test="${attendanceTimeList.size() == 0}">
                従業員種別が登録されていません。<br>
                <a href="">ここから従業員種別を登録してください。</a>
              </c:if>
          </li>
          <li><br><hr><br></li>
        </ul>
        <c:if test="${count != 0}">
        <section id="irregularArea" style="display:none">
        <h2>変形労働情報</h2>
        <ul>
          <li>
            <label>所定時間編集：</label>
            <div>
             <input type="text" name="regularTime" value="${workingTime.getWorkingTime()}" size="2">時間
            </div>
          </li>
        </ul>
        </section>
        </c:if>
      </div>
      <div class="saveButton">
        <input type="submit" value="登録">
      </div>
    </form>
<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
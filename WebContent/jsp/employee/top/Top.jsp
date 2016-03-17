<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/jsp/employee/common/base.jsp">
  <c:param name="content">
<%--ここから下にコンテンツを挿入 --%>

    <h4 class="subTitle">月次労働時間</h4>
    <div class="patternA">
     <ul>
       <li>
         <label>法定労働時間：</label>
         <div>
           <p>${limitWorkingtime.monthlyLegalWorkingTime}</p>
         </div>
       </li>
       <li>
         <label>労働上限時間：</label>
         <div>
           <p>${limitWorkingtime.upperLimitTime}<p>
         </div>
       </li>
       <li>
         <label>法定労働時間までの残時間：</label>
         <div>
           <p><p>${limitWorkingtime.monthlyLegalMessage}</p></p>
         </div>
       </li>
       <li>
         <label>残業可能時間：</label>
         <div>
           <p>${limitWorkingtime.overtimeMessage}</p>
         </div>
       </li>
     </ul>
     </div>


<%--ここから上にコンテンツを挿入 --%>
  </c:param>
</c:import>
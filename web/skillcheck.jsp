<%--
  Created by IntelliJ IDEA.
  User: tera
  Date: 2016/01/29
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <style>

      table{
        margin:100px auto;
      }
      tr:nth-child(even){
        background:#F2F2F2;
      }
      th{
        background:#222222;
        color:white;
      }
      th:nth-child(odd){
        background:#444444;
      }
      th,td{
        padding:5px;
        font-size:small;
      }

    </style>
    <title>スキルチェック</title>
  </head>
  <body>


  <form action="/skill_check_servlet/send_answer?page=1" method="POST">
   <input type="hidden" name="register_id" value="${register_id}"/>

  <table border="1">
    <tr>
      <th>No</th><th>質問カテゴリ</th><th>質問</th><th>備考</th><th>回答</th>
    </tr>
  <c:forEach items="${lstQuestion}" var="question" varStatus="loop">
      <tr>
        <td>${loop.count}</td>
        <td>${question.category}</td>
        <td>${question.question}</td>
        <td>${question.description}</td>
        <td>
          <input type="hidden" name="question_code" value="${question.idQuestion}"/>
          <input type="text" name="answer" value="0" style="width:2em"/>
        </td>
      </tr>
  </c:forEach>
  </table>
    <input type="submit" value="送信"/>
  </form>
  </body>
</html>

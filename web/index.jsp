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
    <title>スキルチェック</title>
  </head>
  <body>
  <form action="/skill_check_servlet" method="get">
  <span>社員番号と</span><input type="text" name="hr_code"/>
  <span>名前を入れてください。</span><input type="text" name="name"/>
<input type="submit" value="スキルチェックを始める"/>
  </form>
  </body>
</html>

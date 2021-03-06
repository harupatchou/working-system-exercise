<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/login/layout.css">
<link rel="stylesheet" type="text/css" href="css/login/contents.css">
<link rel="stylesheet" type="text/css" href="css/login/login.css">
<title>working</title>
</head>
<body>
  <div id="content">
    <div id="header-bk">
      <div id="working-header">
        <c:import url="/jsp/login/common/header.jsp"/>
      </div>
    </div>
    <div id="main-bk">
      <div id="working-main">
        ${param.content}
      </div>
    </div>
    <div id="footer-bk">
      <div id="working-footer">
        <c:import url="/jsp/login/common/footer.jsp"/>
      </div>
    </div>
  </div>
</body>
</html>

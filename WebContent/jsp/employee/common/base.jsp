<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="../js/common/sideMenu.js"></script>
<script src="../js/setup/edit.js"></script>
<link rel="stylesheet" type="text/css" href="../css/employee/layout.css">
<link rel="stylesheet" type="text/css" href="../css/employee/contents.css">
<link rel="stylesheet" type="text/css" href="../css/employee/a_pattern.css">
<title>KOT</title>
</head>
<body>
  <div id="content">
    <div id="header-bk">
      <div id="kot-header">
        <c:import url="/jsp/employee/common/header.jsp"/>
      </div>
    </div>
    <div id="main-bk">
      <div id="kot-main">
        ${param.content}
      </div>
    </div>
    <div id="side-bk">
      <div id="kot-side">
        <c:import url="/jsp/employee/common/sideMenu.jsp"/>
      </div>
    </div>
    <div id="footer-bk">
      <div id="kot-footer">
        <c:import url="/jsp/employee/common/footer.jsp"/>
      </div>
    </div>
  </div>
</body>
</html>

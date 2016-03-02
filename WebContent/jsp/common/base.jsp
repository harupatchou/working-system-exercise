<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<title>KOT</title>
</head>
<body>

<header>
	<c:import url="/jsp/common/header.jsp"/>
</header>
<article>
	${param.content}
</article>
<footer>
	<c:import url="/jsp/common/footer.jsp"/>
</footer>

</body>
</html>

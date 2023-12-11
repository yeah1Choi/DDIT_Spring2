<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COMMON ERROR</title>
</head>
<body>
	<h2>Error !</h2>
	<p>${exception.message }</p>
	
	<ul>
		<c:forEach items="${exception.stackTrace }" var="stack">
			<li>${stack.toString() }</li>
		</c:forEach>
	</ul>
</body>
</html>
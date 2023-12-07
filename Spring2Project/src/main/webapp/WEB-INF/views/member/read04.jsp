<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>READ04 - RESULT</h2>
	<hr>
	
	<h4>carArray</h4>
	<c:forEach items="${carArray}" var="car">
		<c:out value="${car}" /> &nbsp;
	</c:forEach>
	<hr>
	
	<h4>carList</h4>
	<c:forEach items="${carList}" var="car">
		<c:out value="${car}" /> &nbsp;
	</c:forEach>
	<hr>
	
	<h4>hobbyArray</h4>
	<c:forEach items="${hobbyArray}" var="hobby">
		<c:out value="${hobby}" /> &nbsp;
	</c:forEach>
	<hr>
	
	<h4>hobbyList</h4>
	<c:forEach items="${hobbyList}" var="hobby">
		<c:out value="${hobby}" /> &nbsp;
	</c:forEach>
	
</body>
</html>
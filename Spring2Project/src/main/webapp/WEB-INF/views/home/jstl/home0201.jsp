<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>2) set을 활용해 값을 출력할 수 있다.(몸체에 값을 설정 후 값을 출력할 수 있다.)</p>
	<c:set value="${member.userId}" var="id" />
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${id}</td>
		</tr>
	</table>
	
	<c:set var="memId">${member.userId}</c:set>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memId}</td>
		</tr>
	</table>
</body>
</html>
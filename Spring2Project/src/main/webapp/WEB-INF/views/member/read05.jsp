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
	<h2>READ05 - RESULT</h2>
	<hr>
	
	userId : ${user.userId} <br>
	password : ${user.password} <br>
	userName : ${user.userName} <br>
	email : ${user.email} <br>
	birthDay : ${user.birthDay} <br>
	gender : ${user.gender} <br>
	hobby : ${user.hobby} <br>
	
	<p>carArray</p>
	<c:forEach items="${user.carArray}" var="car">
		<c:out value="${car}" />
	</c:forEach>
	
	<p>hobbyArray</p>
	<c:forEach items="${user.hobbyList}" var="hobby">
		<c:out value="${hobby}" />
	</c:forEach>
	
	foreinger : ${user.foreinger} <br>
	developer : ${user.developer} <br>
	nationality : ${user.nationality} <br>
	address.postCode : ${user.address.postCode} <br>
	address.location : ${user.address.location} <br>
		
	<p>cardList</p>
	<c:forEach items="${user.cardList}" var="card">
		<c:out value="${card.no} ${card.validMonth}" />
	</c:forEach>
	
	<p>carList</p>
	<c:forEach items="${user.carList}" var="car">
		<c:out value="${car}" />
	</c:forEach>
	
	introduction : ${user.introduction} <br>
	dateOfBirth : ${user.dateOfBirth}
</body>
</html>
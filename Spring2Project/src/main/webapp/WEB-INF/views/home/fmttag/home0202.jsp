<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>3) type 속성이 currency이다</h4>
	<p>coin : ${coin}</p>
	<fmt:parseNumber value="${coin }" var="coinCurrency" />
	<p>coinNum : ${coinNum}</p>
</body>
</html>
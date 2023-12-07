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
	<h4>4) pattern 속성을 사용해서 직접 사용할 서식을 지정한다.</h4>
	<p>coin : ${coin}</p>
	<fmt:parseNumber value="${coin }" var="coinPattern" pattern="0,000.00"/>
	<p>coinPattern : ${coinPattern}</p>
</body>
</html>
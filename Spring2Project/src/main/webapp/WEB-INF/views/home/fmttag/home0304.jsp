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
	<p>pattern  속성을 이용하여 날짜 출력</p>
	<p>now : ${now}</p>
	<!-- 
		
	 -->
	<p>pattern : <fmt:formatDate value="${now}" pattern="yyy-MM-dd HH:mm:ss"/></p>
	<p>pattern1 : <fmt:formatDate value="${now}" pattern="a h:mm"/></p>
	<p>pattern2 : <fmt:formatDate value="${now}" pattern="z a h:mm"/></p>
</body>
</html>
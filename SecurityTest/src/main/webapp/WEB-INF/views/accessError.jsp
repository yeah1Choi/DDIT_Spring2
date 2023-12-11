<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Access Error</title>
</head>
<body>	
	<h3>Access Error</h3>
	<!-- 
		SPRING-SECURITY_403-EXCEPTION.message : 'Access is denied' 문자열을 출력
		security-context.xml에서 security:access-denied-handler 태그 자체로 설정했을 때 메시지 출력
	 -->
	<h2>${SPRING-SECURITY_403-EXCEPTION.message }</h2>
	<h2>${msg }</h2>
</body>
</html>
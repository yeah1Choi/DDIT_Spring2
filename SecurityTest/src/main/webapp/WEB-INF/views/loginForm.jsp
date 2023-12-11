<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
	<h1>Login</h1>
	<h2>${error }</h2>	<!-- 에러 발생 시, 출력할 메시지 -->
	<h2>${logout }</h2>	<!-- 로그아웃 시, 출력할 메시지 -->
	
	<!-- security를 적용 후 데이터를 전송 시, csrfInput으로 시큐리티 토큰을 전송해야한다. -->
	<form action="/login" method="post">
		username  : <input type="text" name="username" /> <br>
		password  : <input type="text" name="password" /> <br>
		<input type="submit" value="login">
		<sec:csrfInput/>
	</form>
</body>
</html>
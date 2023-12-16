<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>Hello world!</h1>

<P>The time on the server is ${serverTime}.</P>

<br>
<hr>
<br>

<!-- 로그인을 하지않은 경우, isAnonymous() : 익명의 사용자의 경우(로그인을 하지 않은 경우에 해당) -->
<sec:authorize access="isAnonymous()">
	<a href="/login">로그인</a>
</sec:authorize>

<!-- 인증된 사용자인 경우, isAuthenticated() : 인증된 사용면 true -->
<sec:authorize access="isAuthenticated()">
	<a href="/logout">로그아웃</a>
</sec:authorize>

<div>
	<a href="/board/list">Board</a>
</div>
<div>
	<a href="/notice/list">Notice</a>
</div>

</body>
</html>

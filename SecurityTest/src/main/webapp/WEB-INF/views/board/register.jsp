<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD REGISTER</title>
</head>
<body>
	<h2>BOARD REGISTER : Access to member</h2>
	
	<hr>
	
	<sec:authentication property="principal.member.userName" var="name"/>
	<sec:authentication property="principal.member.userId" var="id"/>
	<sec:authentication property="principal.member.userPw" var="pw"/>
	
	<p>사용자명 : ${name }</p>
	<p>아이디 : ${id }</p>
	<p>비밀번호 : ${pw }</p>
	
	<hr>
	
	<p>1. principal : <sec:authentication property="principal"/></p>
	<p>2. principal.member : <sec:authentication property="principal.member"/></p>
	
	<p> 
		<sec:authorize access="hasRole('ROLE_MEMBER')">
			${name }님의 역할명은 회원입니다.
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			${name }님의 역할명은 관리자입니다.
		</sec:authorize>
	</p>
</body>
</html>
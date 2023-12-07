<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FORMHOME</title>
</head>
<body>
	<h3>FORMHOME</h3>
	
	<form action="/board/http/register">
		<input type="submit" value="register(GET)">
	</form>
	<form action="/board/http/register" method="post">
		<input type="submit" value="register(POST)">
	</form>
	
	<form action="/board/http/modify">
		<input type="submit" value="modify(GET)">
	</form>
	<form action="/board/http/modify" method="post">
		<input type="submit" value="modify(POST)">
	</form>
	
	<form action="/board/http/remove" method="post">
		<input type="submit" value="remove(POST)">
	</form>
	
	<form action="/board/http/list">
		<input type="submit" value="list(GET)">
	</form>
</body>
</html>
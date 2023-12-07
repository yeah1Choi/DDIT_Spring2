<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD</title>
</head>
<body>
	<h3>REGISTER</h3>
	
	<form action="/board/post" method="post">
		<button type="submit" name="register">Register</button>
	</form>
	
	<a href="/board/get?list">List</a>
</body>
</html>
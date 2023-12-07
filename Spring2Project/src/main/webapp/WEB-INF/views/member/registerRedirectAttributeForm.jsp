<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>RedirectAttribute 타입</h3>
	<form action="/redirectattribute/register" method="post">
		userId : <input type="text" name="userId" /> <br>
		password : <input type="text" name="password" /> <br>
		msg : <input type="text" name="msg" /> <br>
		<input type="submit" value="전송" />
	</form>
</body>
</html>
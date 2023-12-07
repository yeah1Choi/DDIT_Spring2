<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>3. @ModelAttribute 어노테이션</h3>
	<hr>
	
	<p>2) 기본 자료형인 매개변수에  @ModelAttribute 어노테이션을 지정하여 데이터를 전달한다.</p>
	<form action="/modelattribute/register02" method="post">
		userId : <input type="text" name="userId" /> <br>
		password : <input type="text" name="password" /> <br>
		<input type="submit" value="전송" />
	</form>
</body>
</html>
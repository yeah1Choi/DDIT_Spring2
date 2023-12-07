<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>4. 표현언어(EL)</h3>
	<p>4) empty 연산자를 이용한 방법</p>
	<table border="1">
		<tr>
			<td>\${empty member}</td>
			<td>${empty member}</td>
		</tr>
		<tr>
			<td>\${empty member.userId}</td>
			<td>${empty member.userId}</td>
		</tr>
	</table>
	<!-- 두 결과 모두 false : 이 말은 비어있지 않다는 말 -->
</body>
</html>
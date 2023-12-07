<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="member" method="post" action="/formtag/validation/result">
		<table border="1">
			<tr>
				<td>유저 ID</td>
				<td>
					<form:input path="userId"/>
					<font color="red">
						<form:errors path="userId" />
						<!-- 
						입력하지 않고 넘겨도 이 에러메시지가 안보이고 result로 넘어가짐 
						-> errors 태그 만으로 에러가 보이는게 아님 
						-->
					</font>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<form:input path="userName"/>
					<font color="red">
						<form:errors path="userName" />
					</font>
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<form:input path="email"/>
					<font color="red">
						<form:errors path="email" />
					</font>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>
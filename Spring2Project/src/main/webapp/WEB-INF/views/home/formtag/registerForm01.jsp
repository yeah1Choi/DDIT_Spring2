<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>1) form:form 태그를 이용한 form을 생성해보자</p>
	<!-- 
		modelAttribute : 
		요청에서 만든 key명과 여기 modelAttribute의 명이 같아야한다
	 -->
	<form:form modelAttribute="member" method="post" action="/formtag/register">
		<table border="1">
			<tr>
				<td>유저 ID</td>
				<td>
					<form:input path="userId"/>
					<font>
						<form:errors path="userId" />
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
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>
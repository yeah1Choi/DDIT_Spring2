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
				<td>개발자 여부</td>
				<td>
					<form:checkbox path="developer" />
				</td>
			</tr>
			<tr>
				<td>외국인 여부</td>
				<td>
					<form:checkbox path="foreigner" />
				</td>
			</tr>
			<tr>
				<td>취미(hobby)</td>
				<td>
					<form:checkbox path="hobby" value="Sports" label="Sports"/>
					<form:checkbox path="hobby" value="Dance" label="Dance"/>
					<form:checkbox path="hobby" value="Music" label="Music"/>
				</td>
			</tr>
			<tr>
				<td>취미(hobbyArray)</td>
				<td>
					<form:checkbox path="hobbyArray" value="Sports" label="Sports"/>
					<form:checkbox path="hobbyArray" value="Dance" label="Dance"/>
					<form:checkbox path="hobbyArray" value="Music" label="Music"/>
				</td>
			</tr>
			<tr>
				<td>취미(hobbyList)</td>
				<td>
					<form:checkbox path="hobbyList" value="Sports" label="Sports"/>
					<form:checkbox path="hobbyList" value="Dance" label="Dance"/>
					<form:checkbox path="hobbyList" value="Music" label="Music"/>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>
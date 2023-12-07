<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="member" method="post" action="/formtag/register">
		<table border="1">
			<tr>
				<td>성별</td>
				<td>
					<form:radiobuttons path="gender" items="${genderCodeMap}" />
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>
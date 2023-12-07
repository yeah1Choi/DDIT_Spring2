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
	<form:form modelAttribute="member" method="post" action="/formtag/selectbox/result">
		<table>
			<tr>
				<td>
					국적
				</td>
				<td>
					<form:select path="carList" multiple="true">
						<form:option value="" label="---선택---" />
						<form:options items="${carCodeList}" itemValue="value" itemLabel="label"/>
					</form:select>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>
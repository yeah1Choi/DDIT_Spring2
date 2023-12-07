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
		Map으로 넘긴 데이터를 items 속성에 넣어, map의 key는 value가 되고, map의 value는 label의 몸페 값으로 셋팅된다.
	 -->
	<form:form modelAttribute="member" method="post" action="/formtag/register">
		<table border="1">
			<tr>
				<td>취미(hobbyList)</td>
				<td>
					<span>
						<form:checkboxes items="${hobbyMap}" path="hobbyList"/>
					</span>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>
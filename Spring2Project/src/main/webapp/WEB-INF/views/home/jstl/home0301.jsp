<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>3) c:remove를 이용해 memberId를 삭제한다.</p>
	<c:set value="${member.userId}" var="memberId" />
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memberId}</td>
		</tr>
	</table>
	<!-- 
		[참고]
		세션 처리 시, 작성한 키가 SessionInfo일 때, 
		SessionInfo 안에 들어있는 정보가 member에 해당하는 회원정보일 때,
		세션 안에 들어있는 회원정보를 꺼낼 수 있는 방법은 (EL을 활용)
		${sessionScope.SessionInfo.userId}
	 -->
	<c:set target="${member}" property="userId" value="honggildong"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${member.userId}</td>
		</tr>
	</table>
	
	<c:remove var="memberId"/>
	<p>memberId 변수를 삭제처리함</p>
	<table border="1">
		<tr>
			<td>memberId</td>
			<td>${memberId}</td>
		</tr>
	</table>
</body>
</html>
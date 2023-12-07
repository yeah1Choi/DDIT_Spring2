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
	<p> c:if </p>
	<c:if test="${member.hobbyArray == null}">
		<p>member.hobbyArray == null</p>
	</c:if>
	<c:if test="${member.hobbyArray eq null}">
		<p>member.hobbyArray eq null</p>
	</c:if>
	<p>test 속성에 true나 false를 값으로 가지는 boolean 타입의 변수가 올수 있다</p>
	<c:if test="${member.foreinger}">
		<p>member.hobbyArray eq null</p>
	</c:if>
</body>
</html>
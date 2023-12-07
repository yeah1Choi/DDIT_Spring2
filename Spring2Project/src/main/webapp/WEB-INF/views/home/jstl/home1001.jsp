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
	<c:redirect url="http://localhost/board/list" />
	<!-- 
		리다이렉트된 이후에 아래 내용을 확인할 수 없기 때문에, c:redirect 태그 이후의 코드는 실행되지 않는다.
	 -->
	<p>리다이렉트를 이용하여 list 페이지로 이동합니다</p>
	<h4>페이지 이동방식 : 리다이렉트</h4>
</body>
</html>
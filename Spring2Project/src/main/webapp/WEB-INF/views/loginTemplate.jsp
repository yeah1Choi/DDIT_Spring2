<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- csrf meta 정보를 등록 -->
<meta id="_csrf" name="_csrf" content="${_csrf.token }"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName }"/>

<title>AdminLTE 3 | Log in</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/dist/css/adminlte.min.css">
<script src="${pageContext.request.contextPath }/resources/plugins/jquery/jquery.min.js"></script>

<script type="text/javascript">
var token = "";
var header = "";
$(function(){
	token = $("meta[name = '_csrf']").attr("content");
	header = $("meta[name = '_csrf_header']").attr("content");
});
</script>

</head>

<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message }");
		<c:remove var="message" scope="request"/>
		<c:remove var="message" scope="session"/>
	</script>
</c:if>

<body class="hold-transition ${bodyText }" >

	<!-- content 영역 -->
	<tiles:insertAttribute name="content" />
	
	<script src="${pageContext.request.contextPath }/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/dist/js/adminlte.min.js"></script>
</body>
</html>

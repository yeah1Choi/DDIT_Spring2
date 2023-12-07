<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ITEM3</title>
</head>
<body>
	<h2>LIST</h2>
	<a href="/item3/register">상품 등록</a>
	<table border="1">
	 <tr>
	 	<th align="center" width="80">상품 ID</th>
	 	<th align="center" width="320">상품명</th>
	 	<th align="center" width="100">가격</th>
	 	<th align="center" width="80">편집</th>
	 	<th align="center" width="80">제거</th>
	 </tr>
	 <c:choose>
	 	<c:when test="${empty itemList }">
	 		<tr>
	 			<td colspan="5">조회하신 게시글이 존재하지 않습니다.</td>
	 		</tr>
	 	</c:when>
	 	<c:otherwise>
	 		<c:forEach items="${itemList }" var="item">
	 			<tr>
	 				<td align="center">${item.itemId }</td>
	 				<td align="left">${item.itemName }</td>
	 				<td align="right">${item.price }원</td>
	 				<td align="center"> <a href="/item3/modify?itemId=${item.itemId }">상품 편집</a> </td>
	 				<td align="center"> <a href="/item3/remove?itemId=${item.itemId }">상품 제거</a> </td>
	 			</tr>
	 		</c:forEach>
	 	</c:otherwise>
	 </c:choose>
	</table>
</body>
</html>
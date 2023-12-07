<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>REGISTER</h2>
	<form action="/crud/board/register" method="post" id="registerForm">
		<c:if test="${status eq 'u' }">
			<input type="hidden" name="boardNo" value="${board.boardNo }">
		</c:if>
		<table border="1">
			<tr>
				<td>제목</td>
				<td> <input type="text" name="title" id="title" value="${board.title }"> </td>
			</tr>
			<tr>
				<td>작성자</td>
				<td> <input type="text" name="writer" id="writer" value="${board.writer }"> </td>
			</tr>
			<tr>
				<td>내용</td>
				<td> <textarea rows="10" cols="40" name="content" id="content">${board.content }</textarea> </td>
			</tr>
		</table>
		<div>
			<c:set value="등록" var="btnText"/>
			
			<c:if test="${status eq 'u' }">
				<c:set value="수정" var="btnText"/>
			</c:if>
			
			<input type="button" id="registerBtn" value="${btnText }">
			
			<c:if test="${status eq 'u' }">
				<input type="button" id="cancelBtn" value="취소">
			</c:if>
			
			<c:if test="${status ne 'u' }">
				<input type="button" id="listBtn" value="목록">
			</c:if>
		</div>
	</form>
</body>
<script type="text/javascript">
$(function(){
	var registerForm = $("#registerForm");		// form element
	
	var registerBtn = $("#registerBtn");		// 등록 버튼 element
	var listBtn = $("#listBtn");				// 목록 버튼 element
	var cancelBtn = $("#cancelBtn");			// 취소 버튼 element
	
	registerBtn.on("click", function(){
		var title = $("#title").val();
		var writer = $("#writer").val();
		var content = $("#content").val();
		
		if(title == null || title == ""){
			alert("제목을 입력해주세요 !");
			return false;
		}
		if(writer == null || writer == ""){
			alert("작성자 명을 입력해주세요 !");
			return false;
		}
		if(content == null || content == ""){
			alert("내용을 입력해주세요 !");
			return false;
		}
		
		// 수정일 때 수정 경로로 변경한다.
		if($(this).val() == "수정"){
			registerForm.attr("action","/crud/board/modify");
		}
		
		registerForm.submit();
	});
	
	listBtn.on("click", function(){
		location.href = "/crud/board/list";
	});
	
	cancelBtn.on("click", function(){
		location.href = "/crud/board/read?boardNo=${board.boardNo}";
	});
});
</script>
</html>
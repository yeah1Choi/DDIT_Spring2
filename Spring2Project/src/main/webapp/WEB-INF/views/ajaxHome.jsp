<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAXHOME</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h3>AJAXHOME</h3>
	<hr>
	<form action="" method="get">
		boardNo : <input type="text" name="boardNo" id="boardNo" /> <br>
		title : <input type="text" name="title" id="title" /> <br>
		content : <textarea rows="20" cols="50" name="content" id="content"></textarea> <br> 
		writer : <input type="text" name="writer" id="writer" /> <br>
		<input type="button" id="btn" value="전송" /> <br>
	</form>
	<br>
	<div>
		<h3>Header 매핑</h3>
		<button type="button" id="putBtn">MODIFY(PUT)</button>
		<button type="button" id="putHeaderBtn">MODIFY(PUT with Header)</button>
		<br>
		<h3>Content Type 매핑</h3>
		<button type="button" id="postBtn">MODIFY(POST)</button>
		<button type="button" id="putJsonBtn">MODIFY(PUT JSON)</button>
		<button type="button" id="putXMLBtn">MODIFY(PUT XML)</button>
		<br>
		<h3>Accept 매핑</h3>
		<button type="button" id="getBtn">READ</button>
		<button type="button" id="getJsonBtn">READ(JSON)</button>
		<button type="button" id="getXMLBtn">READ(XML)</button>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		// Header 매핑
		var putBtn = $("#putBtn"); 				// 버튼 putBtn Element
		var putHeaderBtn = $("#putHeaderBtn"); 	// 버튼 putHeaderBtn Element
		
		// Content Type 매핑
		var postBtn = $("#postBtn");			// 버튼 postBtn Element
		var putJsonBtn = $("#putJsonBtn");		// 버튼 putJsonBtn Element
		var putXMLBtn = $("#putXMLBtn");		// 버튼 putXMLBtn Element
		
		// Accept 매핑
		var getBtn = $("#getBtn");				// 버튼 getBtn Element
		var getJsonBtn = $("#getJsonBtn");		// 버튼 getJsonBtn Element
		var getXMLBtn = $("#getXMLBtn");		// 버튼 getXMLBtn Element

		// Header 매핑 시작 //////////////////
		putBtn.on("click", function() {
			var boardNo = $("#boardNo").val(); 	// 번호 데이터
			var title = $("#title").val(); 		// 제목 데이터
			var content = $("#content").val(); 	// 내용 데이터
			var writer = $("#writer").val(); 	// 작성자 데이터

			var boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
			}
			
			// 비동기 처리
			$.ajax({
				type : "put",
				url : "/board/"+boardNo,
				data : JSON.stringify(boardObject),
				contentType : "application/json; charset=utf-8",
				success : function(result){
					console.log("result : "+result);
					// '===' : 강력한 equals
					// 값과 타입과 hash까지 일치하는지 검증
					if(result === "SUCCESS"){
						alert(result);
					}
				}
			});
		});
		
		putHeaderBtn.on("click", function() {
			var boardNo = $("#boardNo").val();	// 번호 데이터
			var title = $("#title").val(); 		// 제목 데이터
			var content = $("#content").val(); 	// 내용 데이터
			var writer = $("#writer").val(); 	// 작성자 데이터
			
			var boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
			}
			
			$.ajax({
				type : "put",
				url : "/board/" + boardNo,
				data : JSON.stringify(boardObject),
				headers:  {
					"X-HTTP-Method-Override" : "PUT"
				},
				contentType : "application/json; charset=utf-8",
				success : function(resilt){
					console.log("result : "+result);
					if(result === "SUCCESS"){
						alert(result);
					}
				}
			});
		});
		// Header 매핑 끝 //////////////////
		
		// Content Type 매핑 시작 //////////////////
		postBtn.on("click",function(){
			var boardNo = $("#boardNo").val();
			var title = $("#title").val();
			var content = $("#content").val();
			var writer = $("#writer").val();
			
			var boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
			}
			
			$.ajax({
				type : "post",
				url : "/board/" + boardNo,
				data : JSON.stringify(boardObject),
				contentType : "application/json; charset=utf-8",
				success : function(result){
					// '==' : Equals operator
					// '===' : Strict Equal Perator
					// '==='는 값을 더 엄격하게 비교할 때 사용
					if(result === "SUCCESS"){
						alert(result);
					}
				}
			});
		});
		
		putJsonBtn.on("click",function(){
			var boardNo = $("#boardNo").val();
			var title = $("#title").val();
			var content = $("#content").val();
			var writer = $("#writer").val();
			
			var boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
			}
			
			$.ajax({
				type : "put",
				url : "/board/" + boardNo,
				data : JSON.stringify(boardObject),
				contentType : "application/json; charset=utf-8",
				success : function(result){
					// '==' : Equals operator
					// '===' : Strict Equal Perator
					// '==='는 값을 더 엄격하게 비교할 때 사용
					if(result === "SUCCESS"){
						alert(result);
					}
				}
			});
		});
		
		putXMLBtn.on("click",function(){
			var boardNo = $("#boardNo").val();
			var title = $("#title").val();
			var content = $("#content").val();
			var writer = $("#writer").val();
			
			var xmlData = "";
			xmlData += "<Board>";
			xmlData += "<boardNo>"+boardNo+"</boardNo>";
			xmlData += "<title>"+title+"</title>";
			xmlData += "<content>"+content+"</content>";
			xmlData += "<writer>"+writer+"</writer>";
			xmlData += "</Board>";
			
			$.ajax({
				type : "put",
				url : "/board/" + boardNo,
				data : xmlData,
				contentType : "application/xml; charset=utf-8",
				success : function(result){
					// '==' : Equals operator
					// '===' : Strict Equal Perator
					// '==='는 값을 더 엄격하게 비교할 때 사용
					if(result === "SUCCESS"){
						alert(result);
					}
				}
			});
		});
		// Content Type 매핑 끝 //////////////////
		
		// Accept 매핑 시작 //////////////////
		getBtn.on("click", function(){
			var boardNo = $("#boardNo").val();
			
			// GET 방식 비동기 HTTP 요청 수행
			$.get("/board/"+boardNo, function(data){
				console.log(data);
				alert(JSON.stringify(data));
			});
		});
		
		getJsonBtn.on("click",function(){
			var boardNo = $("#boardNo").val();
		
			$.ajax({
				type : "get",
				url : "/board/" + boardNo,
				headers : {
					"Accept" : "application/json"
				},
				success : function(result){
					console.log(result);
					alert(JSON.stringify(result));
				}
			});
		});
		
		getXMLBtn.on("click", function(){
			var boardNo = $("#boardNo").val();
			
			$.ajax({
				type : "get",
				url : "/board/" + boardNo,
				headers : {
					"Accept" : "application/xml"
				},
				success : function(result){
					console.log(result);
					alert(xmlToString(result));
				}
			});
		});
		// Accept 매핑 끝 //////////////////
	});
	
	function xmlToString(xmlData){
		var xmlString;
		
		// window.ActiveXObject는 ActiveXObject를 지원하는 브라우저면 
		// Object를 리턴하고 그렇지 않으면 null을 리턴합니다.
		if(window.ActiveXObject){
			xmlString = xmlData.xml;
		} else {
			xmlString = (new XMLSerializer()).serializeToString(xmlData)
		}
		return xmlString;
	}
</script>
</html>
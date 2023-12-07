<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ITEM3</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style type="text/css">
span {
	cursor: pointer;
}
</style>
</head>
<body>
	<h2>REMOVE</h2>
	<form action="/item3/remove" method="post" id="item"
		enctype="multipart/form-data">
		<input type="hidden" name="itemId" value="${item.itemId }">
		<table border="1">
			<tr>
				<td>상품명</td>
				<td><input type="text" name="itemName" id="itemName" value="${item.itemName }" disabled="disabled"/></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price" id="price" value="${item.price }" disabled="disabled"/></td>
			</tr>
			<tr>
				<td>파일</td>
				<td>
					<div class="uploadedList"></div>
				</td>
			</tr>
			<tr>
				<td>개요</td>
				<td><textarea rows="10" cols="30" name="description" disabled="disabled">${item.description }</textarea>
				</td>
			</tr>
		</table>
		<div>
			<button type="submit" id="removeBtn">REMOVE</button>
			<button type="button" id="listBtn">LIST</button>
		</div>
	</form>
</body>
<script type="text/javascript">
	$(function() {
		var item = $("#item"); // form element
		var listBtn = $("#listBtn"); // form element

		// 목록 버튼 클릭 시 목록 화면으로 이동
		listBtn.on("click", function() {
			location.href = "/item3/list";
		});
		
		var itemId = "${item.itemId}";
		console.log("itemId : " + itemId);
		
		// 수정을 하러 들어왔을 때 기존에 업로드했던 파일 정보를 가지고 uploadedList안에 내용을 출력한다.
		$.getJSON("/item3/getAttach/" + itemId, function(list){
			$(list).each(function(){
				console.log("processing...");
				
				var data = this;
				var str = "";
				if (checkImageType(data)) { // 이미지면 이미지 태그를 이용하려 출력
					str += "<div>"
					str += "<a href='/item3/displayFile?fileName=" + data
							+ "'>";
					str += "<img src='/item3/displayFile?fileName="
							+ getThumbnailName(data) + "'/>";
					str += "</a>"
					str += "<span>X</span>"
					str += "</div>"
				} else { // 파일이면 파일명에 대한 링크로만 출력
					str += "<div>"
					str += "<a href='/item3/displayFile?fileName=" + data
							+ "'>" + getOriginalName(data) + "</a>";
					str += "<span>X</span>"
					str += "</div>"
				}

				$(".uploadedList").append(str);
			});
		});

		// 임시 파일로 썸네일 이미지 만들기
		function getThumbnailName(fileName) {
			var front = fileName.substr(0, 12); // 2023/12/04 폴더 경로
			var end = fileName.substr(12); // 뒤 파일명

			console.log("front : " + front);
			console.log("end : " + end);

			return front + "s_" + end;
		}

		// 파일명 추출(원본 파일명)
		function getOriginalName(fileName) {
			if (checkImageType(fileName)) {	// 이미지 파일일 때 리턴
				return;
			}

			var idx = fileName.indexOf("_") + 1;
			return fileName.substr(idx);
		}

		// 이미지 파일인지 검증
		function checkImageType(fileName) {
			var pattern = /jpg|gif|png|jpeg/i;
			return fileName.match(pattern); // 패턴과 일치하면 true (이건 이미지다 !)
		}
	});
</script>
</html>
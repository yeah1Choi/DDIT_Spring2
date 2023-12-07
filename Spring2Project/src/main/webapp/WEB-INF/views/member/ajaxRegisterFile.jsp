<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h3>10. 파일 업로드 Ajax 방식 요청 처리</h3>
	<hr>
	
	<p>Ajax 방식으로 전달할 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리한다.</p>
	
	<div>
		<input type="file" id="inputFile" /> <br>
		<hr>
		<img alt="" src="" id="preview" />
	</div>
</body>
<script type="text/javascript">
$(function(){
	var inputFile = $("#inputFile");
	
	inputFile.on("change", function(event){
		console.log("change!");
		
		var files = event.target.files;
		var file = files[0];
		
		console.log("file : " + file);
		
		/* 
		비동기인데 파일을 보낼 때 formData 이용 !!	
		formData는 key = value 형식으로 데이터가 저장된다. (append 메서드를 이용해서 작성한다.)
		dataType : 응답(response) 데이터를 내보낼 때 보내줄 데이터 타입
		processData : 데이터 파라미터를 data라는 속성으로 넣는데, 제이쿼리 내부적으로 쿼리스트링을 구성합니다.
					파일 전송의 경우 쿼리스트링을 사용하지 않으므로 해당 설정을 false로 비활성화합니다.
		contentType : Content-Type을 설정 시, 사용하는데 해당 설정의 기본값은 
					'application/x-www-form-urlencoded; charset-utf-8' 입니다.
					기본값으로 나가지 않고, 'multipart/form-data'로 나갈 수 있도록 설정을 false 합니다.
					request 요청에서 Content-Type을 확인해보면 
					'multipart/form-data; boundary=====WebkitFormBoundary[HashKey]'
					와 같은 값으로 전송된 것을 확인할 수 있습니다.
		*/
		
		if(isImageFile(file)){	// 이미지 파일일 때
			var formData = new FormData();
			formData.append("file", file);
			
			$.ajax({
				type : "post",
				url : "/ajax/uploadAjax",
				data : formData,
				dataType : "text",
				processData : false,
				contentType : false,
				success : function(data){
					alert(data);
					if(data === "UPLOAD SUCCESS"){
						var file = event.target.files[0];
						var reader = new FileReader();
						reader.onload = function(e){
							$("#preview").attr("src", e.target.result);
						}
						reader.readAsDataURL(file);
					}
				}
			});
		} else {	// 이미지 파일이 아닐 때
			alert("이미지를 넣어주세요 !")
		}
	});
});

// Change 이벤트가 발생할 때 선택된 파일이 이미지인지 검증
function isImageFile(file){
	var ext = file.name.split(".").pop().toLowerCase();	// 파일명에서 확장자를 가져온다.
	
	// 확장자 중 이미지에 해당하는 확장자가 아닌 경우 포함되어 있는 문자가 없으니까 -1(false)를 리턴
	// 확장자 중 이미지 확장자가 포함되어 있다면 0보다 큰 수 일테니 true가 리턴
	return ($.inArray(ext, ["jpg", "jpeg", "gif", "png", "jfif"]) === -1) ? false : true;
}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/es6-promise/4.1.1/es6-promise.auto.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
</head>
<body>
	<div id="capture" style="padding: 10px; background: #f5da55">
		<h4 style="color: #000;">Hello world!</h4>
	</div>
	<button id="pdfBtn">PDF 다운로드</button>
</body>
<script type="text/javascript">
	function createPdf(){
		html2canvas(document.querySelector("#capture")).then(canvas => {
			// base64 url 로 변환
			var imgData = canvas.toDataURL('image/jpeg');
			
			var imgWidth = 210; // 이미지 가로 길이(mm) A4 기준
			var pageHeight = imgWidth * 1.414; // 출력 페이지 세로 길이 계산 A4 기준
			var imgHeight = canvas.height * imgWidth / canvas.width;
			var heightLeft = imgHeight;
			var margin = 20;
			
			var doc = new jsPDF('p', 'mm', 'a4');
			var position = 0;
			
			// 첫 페이지 출력
			doc.addImage(imgData, 'jpeg', margin, position, imgWidth, imgHeight);
			heightLeft -= pageHeight;
			
			// 한 페이지 이상일 경우 루프 돌면서 출력
			while (heightLeft >= 20) {
				position = heightLeft - imgHeight;
				doc.addPage();
				doc.addImage(imgData, 'jpeg', margin, position, imgWidth, imgHeight);
				heightLeft -= pageHeight;
			}
			
			// 파일 저장
			doc.save('sample.pdf');
		});
	}
	
	$("#pdfBtn").on("click", function(){
		createPdf();
	});
</script>
</html>
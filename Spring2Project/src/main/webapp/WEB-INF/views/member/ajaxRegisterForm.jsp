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
	<h3>Ajax 방식 요청 처리</h3>
	<hr>
	
	<form>
		userId : <input type="text" name="userId" id="userId" /> <br>
		password : <input type="text" name="password" id="password" /> <br>
	</form>
	
	<p>3) 객체 타입의 JSON 요청 데이터 @RequestBody 어노테이션을 지정하여 자바빈즈 매개변수로 처리한다.</p>
	<button type="button" id="registerBtn03">RegisterBtn03</button>
	
	<p>4) 객체 타입의 JSON 요청 데이터는 문자열 매개변수로 처리할 수 없다.</p>
	<button type="button" id="registerBtn04">RegisterBtn04</button>	
	
	<p>5) 요청 URL에 쿼리 파라미터를 붙여서 전달하면 문자열 매개변수로 처리한다.</p>
	<button type="button" id="registerBtn05">RegisterBtn05</button>	
	
	<p>7) 객체 배열 타입의 JSON 요청 데이터를 자바빈즈 요소를 가진 리스트 컬렌션 매개변수에 @RequstBody 어노테이션을 지정하여 처리한다.</p>
	<button type="button" id="registerBtn07">RegisterBtn07</button>	
	
	<p>8) 중첩된 객체 차입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.</p>
	<button type="button" id="registerBtn08">RegisterBtn08</button>	
	
	<p>9) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 컬렉션 리스트 매개변수로 처리한다.(객체 안에 들어있는 리스트 변수)</p>
	<button type="button" id="registerBtn09">RegisterBtn09</button>	
</body>
<script type="text/javascript">
$(function(){
	var registerBtn03 = $("#registerBtn03");
	var registerBtn04 = $("#registerBtn04");
	var registerBtn05 = $("#registerBtn05");
	var registerBtn07 = $("#registerBtn07");
	var registerBtn08 = $("#registerBtn08");
	var registerBtn09 = $("#registerBtn09");
	
	registerBtn03.on("click", function(){
		var userId = $("#userId").val();
		var password = $("#password").val();
		
		var userObject = {
				userId : userId,
				password : password
		}
		
		$.ajax({
			type : "post",
			url : "/ajax/register03",
			data : JSON.stringify(userObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log(result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		})
	});
	
	registerBtn04.on("click", function(){
		var userId = $("#userId").val();
		var password = $("#password").val();
		
		var userObject = {
				userId : userId,
				password : password
		}
		$.ajax({
			type : "post",
			url : "/ajax/register04",
			data : JSON.stringify(userObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log(result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		});
	});
	
	registerBtn05.on("click", function(){
		var userId = $("#userId").val();
		var password = $("#password").val();
		
		var userObject = {
				userId : userId,
				password : password
		}
		$.ajax({
			type : "post",
			url : "/ajax/register05?userId="+userId,
			data : JSON.stringify(userObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log(result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		});
	});
	
	registerBtn07.on("click",function(){
		var userObjectArray = [
			{userId : "name01", password : "pw1"},
			{userId : "name02", password : "pw2"}
		];
		$.ajax({
			type : "post",
			url : "/ajax/register07",
			data : JSON.stringify(userObjectArray),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log(result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		});
	});
	
	registerBtn08.on("click", function(){
		var userId = $("#userId").val();
		var password = $("#password").val();
		
		var userObject = {
				userId : userId,
				password : password,
				address : {
					postCode : "010908",
					location : "Daejeon"
				}
		}
		
		$.ajax({
			type : "post",
			url : "/ajax/register08",
			data : JSON.stringify(userObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log(result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		});
	});
	
	registerBtn09.on("click", function(){
		var userId = $("#userId").val();
		var password = $("#password").val();
		
		var userObject = {
				userId : userId,
				password : password,
				cardList : [
					{no : "12345", validMonth : "20221018"},
					{no : "45678", validMonth : "20231205"}
				]
		}
		
		$.ajax({
			type : "post",
			url : "/ajax/register09",
			data : JSON.stringify(userObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log(result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		});
	});
});
</script>
</html>
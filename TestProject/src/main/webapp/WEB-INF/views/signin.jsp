<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-header align-items-start min-vh-100"
	style="background-image: url('https://images.unsplash.com/photo-1497294815431-9365093b7331?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80');">
	<span class="mask bg-gradient-dark opacity-6"></span>
	<div class="container my-auto">
		<div class="row">
			<div class="col-lg-4 col-md-8 col-12 mx-auto">
				<div class="card z-index-0 fadeIn3 fadeInBottom">
					<div
						class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
						<div
							class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
							<h4 class="text-white font-weight-bolder text-center mt-2 mb-0">로그인</h4>
						</div>
					</div>
					<div class="card-body">
						<form role="form" class="text-start" method="post" action="/loginCheck.do" id="loginForm">
							<font class="text-primary font-weight-bold text-xs mt-1 mb-0 error">${errors.memId }</font>
							<div class="input-group input-group-outline my-3">
								<input type="text" class="form-control" id="memId" name="memId" placeholder="아이디">
							</div>
							<font class="text-primary font-weight-bold text-xs mt-1 mb-0 error">${errors.memPw }</font>
							<div class="input-group input-group-outline mb-3">
								<input type="password" class="form-control" id="memPw" name="memPw" placeholder="비밀번호">
							</div>
							<div class="text-center">
								<button type="button" class="btn bg-gradient-primary w-100 my-4 mb-2" id="loginBtn">로그인</button>
							</div>
							<p class="mt-4 text-sm text-center">
								아직 회원이 아닌가요? 
								<a href="/signup.do" class="text-primary text-gradient font-weight-bold">회원가입</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(function(){
	var loginBtn = $("#loginBtn");
	var loginForm = $("#loginForm");
	
	loginBtn.on("click", function(){
		var id = $("#memId").val();
		var pw = $("#memPw").val();
		
		if(id == null || id == ""){
			alert("아이디를 입력해주세요");
			return false;
		}
		
		if(pw == null || pw == ""){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		
		loginForm.submit();
	});
});
</script>

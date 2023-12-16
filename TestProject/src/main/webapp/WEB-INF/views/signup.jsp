<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<div class="page-header min-vh-100">
   <div class="container">
      <div class="row">
         <div class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
            <div class="position-relative bg-gradient-info h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center"
               style="background-image: url('${pageContext.request.contextPath}/resources/assets/img/illustrations/illustration-lock.jpg'); background-size: cover;">
            </div>
         </div>
         <div
            class="col-xl-4 col-lg-5 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5">
            <div class="card card-plain">
               <div class="card-header">
                  <h4 class="font-weight-bolder">회원가입</h4>
                  <p class="mb-0">회원등록 후, 저희 서비스와 함께해요!</p>
               </div>
               <div class="card-body">
                  <form role="form" method="post" action="/signup.do" id="signupForm">
                     <font class="font-weight-bold text-xs mt-1 mb-0 error"><!-- 아이디 에러처리 --></font>
                     <div class="input-group input-group-outline mb-3">
                        <input type="text" class="form-control" id="memId" name="memId" placeholder="아이디">
                     </div>
                     <font class="text-primary font-weight-bold text-xs mt-1 mb-0 error"><!-- 비밀번호 에러처리 --></font>
                     <div class="input-group input-group-outline mb-3">
                        <input type="text" class="form-control" id="memPw" name="memPw" placeholder="비밀번호">
                     </div>
                     <div class="input-group input-group-outline mb-3">
                        <input type="text" class="form-control" id="memPwRe" placeholder="비밀번호 재입력">
                     </div>
                     <font class="text-primary font-weight-bold text-xs mt-1 mb-0 error"><!-- 이름 에러처리 --></font>
                     <div class="input-group input-group-outline mb-3">
                        <input type="text" class="form-control" id="memName" name="memName" placeholder="이름">
                     </div>
                     <font class="font-weight-bold text-xs mt-1 mb-0 error"><!-- 닉네임 에러처리 --></font>
                     <div class="input-group input-group-outline mb-3">
                        <input type="text" class="form-control" id="memNickname" name="memNickname" placeholder="닉네임">
                        <input type="button" class="form-control" id="nickChkBtn" value="중복확인"/>
                     </div>
                     <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" id="agree" value="Y"> 
                        <label class="form-check-label" for="agree">개인정보 동의</label>
                     </div>
                     <font class="text-primary font-weight-bold text-xs mt-1 mb-0 error"></font>
                     <div class="text-center">
                        <button type="button" class="btn btn-lg bg-gradient-primary btn-lg w-100 mt-4 mb-0" id="signupBtn">가입하기</button>
                     </div>
                  </form>
               </div>
               <div class="card-footer text-center pt-0 px-lg-2 px-1">
                  <p class="mb-2 text-sm mx-auto">
                     우리 서비스 회원이세요? 
                     <a href="/signin.do" class="text-primary text-gradient font-weight-bold">로그인</a>
                  </p>
                  <font class="text-primary font-weight-bold text-xs mt-1 mb-0 error"><!-- 가입하기 에러처리 --></font>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>
<script type="text/javascript">
$(function(){
   var memId = $("#memId");
   var nickChkBtn = $("#nickChkBtn");
   var signupBtn = $("#signupBtn");
   var signupForm = $("#signupForm");
   var idFlag = false; //중복 체크
   var nickFlag = false; // 닉네임 중복체크 여부 flag
   
   // 아이디를 입력하기 위한 element에 아이디를 입력할때만다 해당 이벤트
   memId.keyup(function(){
      var id =$(this).val();
      
      if(id.length < 4){
         errPrint(0, "아이디는 4자리부터 시작합니다!", "red");
         return false;
      }   
      
      var data = {
            
            memId : id
      }
      
      $.ajax({
         type : "post",
         url : "/idCheck.do",
         data : JSON.stringify(data),
         contentType : "application/json; cherset=utf-8",
         success : function(res){
            
            if(res === "NOTEXIST"){
               errPrint(0, " 사용가능한 아이디입니다!", "green");
               idFlag = true;
            }else{
               errPrint(0, "아이디가 중복됩니다!", "red");
               idFlag = false;
            }
         }
         
         
         
      });
   });  
   
   nickChkBtn.on("click", function(){
	  var nickname = $("#memNickname").val();
	  
	  if(nickname == null || nickname == ""){
		  errPrint(3, "닉네임을 입력해주세요.", red);
		  return false;
	  }
	  
	  var data = {
			memNickname : nickname
	  }
	  
	  $.ajax({
		 type : "post",
		 url : "/nickNameCheck.do",
		 data : JSON.stringify(data),
		 dataType : "text",
		 contentType : "application/json;charset=utf-8",
		 success : function(res){
			 if(res === "NOTEXIST"){
				 errPrint(3, "사용가능한 닉네임입니다.", "green");
				 nickFlag = true;
			 } else {
				 errPrint(3, "닉네임이 중복됩니다.", "red");
				 nickFlag = false;
			 }
		 }
	  });
   });
   
   signupBtn.on("click", function(){
	  var color = "red";
	  var id = $("#memId").val();
	  var pw = $("#memPw").val();
	  var pwre = $("#memPwRe").val();
	  var name = $("#memName").val();
	  var nickname = $("#memNickname").val();
	  var agree = $("#agree:checked").val();
	  var pwFlag = true; // 비밀번호 일치 여부 (비밀번호 = 재입력비밀번호)
	  var agreeFlag = false;
	  
	  errInit();
	  
	  if(id == null || id == ""){
		  errPrint(0, "아이디를 입력해주세요.", color);
		  return false;
	  }
	  
	  if(pw == null || pw == ""){
		  errPrint(1, "비밀번호를 입력해주세요.", color);
		  return false;
	  }
	  
	  if(pwre == null || pwre == ""){
		  errPrint(1, "비밀번호 재입력을 입력해주세요.", color);
		  return false;
	  }
	  
	  if(pw != pwre){
		  errPrint(1, "비밀번호가 일치하지 않습니다.", color);
		  pwFlag = false; // 비밀번호 불일치
		  return false;
	  }
	  
	  if(name == null || name == ""){
		  errPrint(2, "이름를 입력해주세요.", color);
		  return false;
	  }
	  
	  if(nickname == null || nickname == ""){
		  errPrint(3, "닉네임를 입력해주세요.", color);
		  return false;
	  }
	  
	  if(agree != 'Y'){
		  errPrint(4, "개인정보 동의에 체크해주세요.", color);
		  return false;
	  } else {
		  agreeFlag = true;
	  }
	  
	  if(pwFlag && idFlag && nickFlag && agreeFlag){
		  signupForm.submit();
	  } else {
		  if(!pwFlag) errPrint(5, "비밀번호가 일치하지 않아 진행할 수 없습니다.", color);
		  if(!idFlag) errPrint(5, "아이디 중복 체크해주세요.", color);
		  if(!nickFlag) errPrint(5, "닉네임 중복 체크해주세요.", color);
		  if(!agreeFlag) errPrint(5, "개인정보 동의에 체크되어 있지 않습니다.", color);
	  }
   });
});

function errPrint(cnt, comp, color){
   $(".error:eq("+ cnt +")").text(comp).attr("color", color);
}

function errInit(){
	$(".error").text("");
}
</script>
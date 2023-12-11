                                                                                                                                                                       package kr.or.ddit.controller.validation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/validation")
public class validationController {
	/*
	 * 	[ 9장. 입력 유효성 검증 ]
	 * 		1. 입력값 검증
	 * 			- 스프링 MVS Bean Validation 기능을 이용해 요청 파라미터 값이 바인딩된 도메인 클래스(또는 커멘드 클래스)의 입력값 검증을 한다
	 * 
	 * 		[환경설정]
	 * 		# 의존 관계 정의
	 * 		- 입력값 검증을 위한 의존 라이브러리를 추가한다.
	 * 		- pom.xml에서 hibernate-validator 추가
	 * 
	 * 		# 입력값 검증 활성화
	 * 		- Member 클래스로 넘어가서 임시 테스트로 userId, userName 규칙을 활성화한다.
	 * 		- 이때, 규칙을 활성화하기 위해서 사용할 어노테이션이 있다.
	 * 			> @Validated를 지정한다
	 * 			> 입력값 검증 대상의 도메인 클래스 직후에 BindingResult를 정의한다
	 * 			BindingResult에는 요청 데이터의 바인딩 오류와 입력값 검증 오류 정보가 저장된다.
	 * 			
	 * 			** 바인딩 오류
	 * 			: 프로그래밍에서 변수나 함수와 같은 식별자와 그에 해당하는 값 또는 동작을 서로 연결하는 과정에서 발생할 수 있는 오류
	 * 		
	 * 		# 입력값 검증 환경설정 순서
	 * 		1. 입력값 검증을 위한 의존 라이브러리 추가
	 * 		2. 입력값 검증 활성화
	 * 			> 활성화를 할 도메인 클래스 안에 @Validated 어노테이션을 지정
	 * 		3. 도메인 클래스 내 필드에다가 검증을 위한 어노테이션들로 데이터 검증을 설정한다. (@NotBlank, @Size 등등)
	 * 		4. 에러를 받을 BindingResult를 설정한다. (컨트롤러 메서드 내 설정)
	 */
	
	@RequestMapping(value="/registerValidationForm01", method=RequestMethod.GET)
	public String registerValidationForm01(Model model) {
		log.info("registerValidationForm01() 실행");
		
		model.addAttribute("member", new Member());
		
		return "validation/registerValidationForm01";
	}
	
	// 결과를 처리할 메소드
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String registerValidationFormResult(@Validated Member member, BindingResult result) {
		log.info("registerValidationFormResult() 실행");
		
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		
		if(result.hasErrors()) {
			return "validation/registerValidationForm01";
		}
		return "validation/success";
	}
	
	/*
	 * 		2. 입력값 검증 결과
	 * 			- 입력값 검증 대상의 도메인 클래스 직후에 BindingResult를 정의한다.
	 * 
	 * 			#BindingResult에는 요청 데이터의 바인딩 에러와 입력값 검증 예러 정보가 저장된다.
	 * 			
	 * 			1) 에러 정보 확인을 위한 BindingResult 메서드
	 * 				hasErrors() : 에러가 발생한 경우 true 반환
	 * 				hasGlabalErrors() : 객체 레벨의 에러가 발생한 경우 true 반환
	 * 				hasFieldErrors() : 필드 레벨의 에러가 발생한 경우 true 반환
	 * 				hasFieldErrors(String) : 인수에 지정한 필드에서 에러가 발생한 경우 true 반환
	 */
	
	@RequestMapping(value="/registerValidationForm02", method=RequestMethod.GET)
	public String registerValidationForm02(Model model) {
		log.info("registerValidationForm02() 실행");
		
		model.addAttribute("member", new Member());
		
		return "validation/registerValidationForm02";
	}
	
	@RequestMapping(value="/result2", method=RequestMethod.POST)
	public String registerValidationForm02Result(@Validated Member member, BindingResult result) {
		log.info("registerValidationForm02Result() 실행");
		log.info("result.hasErrors() : "+result.hasErrors());
		
		if(result.hasErrors()) {	// 에러 정보가 존재
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErrors = result.getGlobalErrors();
			List<FieldError> fieldErrors = result.getFieldErrors();
			
			log.info("allErrors.size() : " + allErrors.size());
			log.info("globalErrors.size() : " + globalErrors.size());
			log.info("fieldErrors.size() : " + fieldErrors.size());
					
			// 객체와 필드 레벨의 에러정보 모두를 출력한다.
			for(int i =0;i<allErrors.size();i++) {
				ObjectError objError = allErrors.get(i);
				log.info("allError : "+objError+"\n");
			}
			// 객체 레벨의 에러정보를 출력한다.
			for(int i = 0; i < globalErrors.size(); i++) {
				ObjectError objError = globalErrors.get(i);
				log.info("globalErrors : " + objError + "\n");
			}
			// 객체와 필드 레벨의 에러정보 모두를 출력한다.
			for(int i = 0; i < fieldErrors.size(); i++) {
				FieldError fieldError = fieldErrors.get(i);
				log.info("fieldError : " + fieldError + "\n");
				log.info("fieldError.getDefaultMessage() : " + fieldError.getDefaultMessage());
			}
			
			return "validation/registerValidationForm02";
		}
		
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		log.info("userEmail : " + member.getEmail());
		log.info("userGender : " + member.getGender());
		
		return "validation/success";
	}
	
	/*
	 * 		3. 입력값 검증 규칙
	 * 			- 입력값 검증 규칙은 Bean Validation이 제공하는 제약 어노테이션으로 설정한다.
	 * 				검사 규칙은 세가지로 분류
	 * 				- Bean Validation 표준 제약 어노테이션
	 * 				- 서드파티에서 구현한 제약 어노테이션(서드파티란 제3자에서 만든)
	 * 				- 직접 구현한 제약 어노테이션
	 * 			1) Member 클래스에서 테스트를 위한 어노테이션으로 설정 (아래 명시되어있는 내용들을 가지고 테스트 해보길!)
	 * 			- @NotNull : 빈값이 아닌지 검사
	 * 			- @Null : null인지 검사
	 * 			- @NotBlank : 문자열이 null이 아니고 trim한 길이가 0보다 크다는 것 검사
	 * 			- @NotEmpty : 문자열이 null이거나 비어있는지 검사
	 * 			- @Size : 글자 수나 컬렉션의 요소 개수를 검사
	 * 				> @Size(max=, min=)
	 * 			- @Max(value=) : value보다 작거나 같은 걸 검사
	 * 			- @Min(value=) : value보다 크거나 같은 걸 검사
	 * 			- @Email : email 주소 형식인지 검사
	 * 			- @Past : 과거 날짜인지 검사
	 * 			- @Future : 미래 날짜인지 검사
	 * 			- @Pattern(regexp=) : Sequence는 지정된 정규식과 일치해야하고, 정규식은 Java 정규식 규칙을 따른다.
	 * 			- @Positive : 양수여야합니다 (0은 에러)
	 * 			- @PositiveOrZero : 양수 도는 0이어야합니다
	 * 			- @Length(max=, min=) : 문자열의 길이가 min과 max 사인지 검사
	 * 
	 * 			[ 테스트 ]
	 * 			- Member 클래스의 검증 활성화 추가
	 * 				> userId, password, userName, email, dateOfBirth
	 * 			- 테스트는 registerValidationForm02에서 진행(시나리오를 기반으로)
	 * 
	 * 		4. 중첩된 자바빈즈 입력값 검증
	 * 			- 중첩된 자바빈즈와 자바빈즈의 컬렉션에서 정의한 프로퍼티에 대해 입력값 검증을 할 때는 @Valid를 지정
	 * 
	 * 			1) 중첩된 자바빈즈 클래스를 정의하고 @Valid를 지정한다.
	 * 				- Member 클래스 내 Address address 필드에 @Valid 어노테이션 지정
	 * 				- Member 클래스 내 List<Card> cardList 필드에 @Valid 어노테이션 지정
	 * 			2) Address 클래스 내에도 validation을 설정
	 * 			3) Card 클래스 내에도 validation을 설정
	 */
	
	@RequestMapping(value="/registerValidationForm03", method=RequestMethod.GET)
	public String registerValidationForm03(Model model) {
		log.info("registerValidationForm03() 실행");
		
		model.addAttribute("member", new Member());
		
		return "validation/registerValidationForm03";
	}
	
	@RequestMapping(value="/result3", method=RequestMethod.POST)
	public String registerValidationForm03Result(@Validated Member member, BindingResult result) {
		log.info("registerValidationForm03Result() 실행");
		
		if(result.hasErrors()) {
			return "validation/registerValidationForm03";
		}
		
		log.info("userId : "+member.getUserId());
		log.info("dateOfBirth : "+member.getDateOfBirth());
		
		Address address = member.getAddress();
		if(address != null) {
			log.info("postCode : "+address.getPostCode());
			log.info("location : "+address.getLocation());
		}
		
		List<Card> cardList = member.getCardList();
		if(cardList != null && cardList.size() > 0) {
			for(int i=0;i<cardList.size();i++) {
				Card card = cardList.get(i);
				log.info("no : "+card.getNo());
				log.info("validMonth : "+card.getValidMonth());
			}
		}
		return "validation/success";
	}
}

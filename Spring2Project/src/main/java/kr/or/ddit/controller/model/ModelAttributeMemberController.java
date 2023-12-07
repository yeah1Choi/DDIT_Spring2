package kr.or.ddit.controller.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/modelattribute")
public class ModelAttributeMemberController {
	/*
	 * 		3. @ModelAttribute 어노테이션
	 * 			- @ModelAttribute은 전달받은 매개변수를 강제로 Model에 담아서 전달하도록할 때 필요한 어노테이션이다.
	 */
	
	// 
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm() 실행");
		return "member/registerModelAttributeForm";
	}
	
	// 2) 기본 자료형인 매개변수에 @ModelAttribute 어노테이션을 지정하여 데이터를 전달한다.
	// 이 때, 어노테이션을 적용하지 않은 기본 자료형인 경우엔 데이터를 전달할 수 없다.
	@RequestMapping(value="/register02",method=RequestMethod.POST)
	public String register02(
		 	@ModelAttribute("userId") String userId,
		 	@ModelAttribute("password") String password
			) {
		log.info("register02() 실행");
		log.info("userId : " + userId);
		log.info("password : " + password);
		
		return "result";
	}
	
	// 3) 자바빈즈 규칙에 맞는 객체는 매개변수로 선언하면 기본적으로 전달된다.
	@RequestMapping(value="/register03",method=RequestMethod.POST)
	public String register03(Member member) {
		// Member 객체가 앞자리가 소문자로 바꿔 키가 된다 member가 키가 아니다
		log.info("register03() 실행");
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		
		return "result";
	}
}

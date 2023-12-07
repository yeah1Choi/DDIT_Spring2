package kr.or.ddit.controller.jsp.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/validation")
public class JSPFormValidationController {
	/*
	 * 		15. 입력값 검증 에러
	 * 			- 입력값 검증 에러를 출력하려면 <form:errors> 요소 사용
	 */

	@RequestMapping(value="/registerFormValidation01", method=RequestMethod.GET)
	public String registerFormValidation01(Model model) {
		log.info("registerFormValidation01() 실행");
		
		model.addAttribute("member", new Member());

		return "home/formtag/validation/registerFormValidation01";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String registerFormValidationResult(Member member) {
//		Member를 파라미터로 두면 key로 return되어 넘어가기 때문에 model 객체를 사용하지 않아도 결과가 보여짐
		log.info("registerFormValidationResult() 실행");
		
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		log.info("userEmail : " + member.getEmail());

		return "home/formtag/validation/result";
	}
}

package kr.or.ddit.controller.jsp.password;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/formtag/password")
public class JSPFormPasswordTagController {
	/*
	 * 	5. 패스쿼드 필드 요소
	 * 		- HTML패스워드 필드를 출력하려면 <form:password> 요소를 사용한다.
	 */
	
	@RequestMapping(value = "/registerForm01", method=RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01() 실행");
		model.addAttribute("member", new Member());
		return "home/formtag/password/registerForm01";
	}
	
	@RequestMapping(value = "/registerForm02", method=RequestMethod.GET)
	public String registerForm02(Model model) {
		log.info("registerForm02() 실행");
		Member member = new Member();
		// 값을 설정해서 뷰에 전달하더라도 패스워드 필드에 반영되지 않는다 (패스워드는 특수한 경우기 때문에 값이 바인딩 되지않음)
		member.setPassword("987987987");
		model.addAttribute("member", member);
		return "home/formtag/password/registerForm01";
	}
}

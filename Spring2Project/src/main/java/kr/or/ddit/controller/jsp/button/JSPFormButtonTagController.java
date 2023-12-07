package kr.or.ddit.controller.jsp.button;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/button")
public class JSPFormButtonTagController {
	/*
	 * 		14. 버튼 요소
	 * 			- HTML 버튼을 출력하려면 <form:button> 요소 사용
	 */

	@RequestMapping(value="/registerFormButton01", method=RequestMethod.GET)
	public String registerFormButton01(Model model) {
		log.info("registerFormButton01() 실행");
		
		Member member = new Member();
		
		model.addAttribute("member", member);

		return "home/formtag/button/registerFormButton01";
	}
}

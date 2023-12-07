package kr.or.ddit.controller.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/redirectattribute")
public class RedirectAttributeMemberController {
	/*
	 * 		4. RedirectAttribute 타입
	 * 			- RedirectAttribute는 일회성 데이터를 전달하는 용도로 사용한다.
	 */
	
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm() 실행");
		return "member/registerRedirectAttributeForm";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Member member, String msg, RedirectAttributes ra) {
		log.info("register() 실행");
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		log.info("msg : " + msg);
		
		ra.addFlashAttribute("msg", msg);
		return "redirect:/redirectattribute/result";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.GET)
	public String result() {
		log.info("result() 실행");
		return "result";
	}
}

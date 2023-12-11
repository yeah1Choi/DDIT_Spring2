package kr.or.ddit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.security.CustomAccessDeniedHandler;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	// 사용자가 정의한 로그인 페이지를 요청할 컨트롤러 메소드
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(String error, String logout, Model model) {
		log.info("error : " + error);
		log.info("logout : " + logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error !");
		}
		if(logout != null) {
			model.addAttribute("logout", "Loginout !");
		}
		
		return "loginForm";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutForm() {
		return "logoutForm";
	}
}

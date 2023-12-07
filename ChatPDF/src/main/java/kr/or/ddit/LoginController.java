package kr.or.ddit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/loginProcess.do", method = RequestMethod.POST)
	public String loginProcess(@RequestParam String id, HttpServletRequest request) {
		
		log.info("Welcome "+id);
		
    	HttpSession session = request.getSession();
    	session.setAttribute("id", id);
		return "chat";
	}
	
}
package kr.or.ddit.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	private static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	@RequestMapping(value = "/accessError")
	public String accessDenied(Authentication auth, Model model) {
		log.info("Access Denied : " + auth);
		model.addAttribute("msg", "Access Denied");
		return "accessError";
	}
	
}

package kr.or.ddit.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardContoller {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		log.info("list : access to all");
		return "board/list";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		log.info("register : access to member");
		return "board/register";
	}
}

package kr.or.ddit.controller.jsp.checkbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/checkbox")
public class JSPFormCheckboxTagcontroller {
	/*
	 * 		8. 체크박스 요소
	 * 			- HTML 체크박스를 출력하려면 <form:checkbox> 요소를 사용한다.
	 */
	
	@RequestMapping(value = "/registerFrom01", method=RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01() 실행");
		
		model.addAttribute("member", new Member());
		return "home/formtag/checkbox/registerForm01";
	}
	
	@RequestMapping(value = "/registerFrom02", method=RequestMethod.GET)
	public String registerFrom02(Model model) {
		log.info("registerFrom02() 실행");
		
		Member member = new Member();
		member.setDeveloper("Y");
		member.setForeinger(true);
		member.setHobby("Movie");
		String[] hobbyArray = {"Music", "Movie"};
		member.setHobbyArray(hobbyArray);
		
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("Music");
		hobbyList.add("Movie");
		member.setHobbyList(hobbyList);
		
		model.addAttribute("member", member);
		return "home/formtag/checkbox/registerFrom02";
	}
}

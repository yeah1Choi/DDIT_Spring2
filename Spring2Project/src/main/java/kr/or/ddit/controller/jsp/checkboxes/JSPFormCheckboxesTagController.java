package kr.or.ddit.controller.jsp.checkboxes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.CodeLabelValue;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/checkboxes")
public class JSPFormCheckboxesTagController {
	/*
	 * 		7. 여러개의 체크박스 요소
	 * 			- HTML 여러개의 체크박스를 출력하려면 <form:checkboxes>요소를 사용한다.
	 */
	
	@RequestMapping(value = "/registerFrom01", method=RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01() 실행");
		
		Map<String, String> hobbyMap = new HashMap<String, String>();
		hobbyMap.put("01", "Sport");
		hobbyMap.put("02", "Music");
		hobbyMap.put("03", "Dance");
		
		model.addAttribute("hobbyMap", hobbyMap);
		model.addAttribute("member", new Member());
		return "home/formtag/checkboxes/registerForm01";
	}
	
	@RequestMapping(value = "/registerForm02", method=RequestMethod.GET)
	public String registerForm02(Model model) {
		log.info("registerForm02() 실행");
		
		List<CodeLabelValue> hobbyCodeList = new ArrayList<CodeLabelValue>();
		
		model.addAttribute("hobbyCodeList", hobbyCodeList);
		model.addAttribute("member", new Member());
		return "home/formtag/checkboxes/registerForm02";
	}
}

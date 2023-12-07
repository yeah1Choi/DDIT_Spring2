package kr.or.ddit.controller.jsp.radio;

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
@RequestMapping("formtag/radio")
public class JSPFormRadioTagController {
	/*
	 * 	9. 여러 개의 라디오 버튼 요소
	 * 		- HTML 라디오 버튼을 출력하려면 <form:radiobuttons>요소를 사용한다.
	 */
	
	@RequestMapping(value = "/registerFormRadios01", method = RequestMethod.GET)
	public String registerFormRadios01(Model model) {
		log.info("registerFormRadios01() 실행");
		
		Map<String, String> genderCodeMap = new HashMap<String, String>();
		genderCodeMap.put("01", "Male");
		genderCodeMap.put("02", "Female");
		genderCodeMap.put("03", "Other");
		
		model.addAttribute("genderCodeMap", genderCodeMap);
		model.addAttribute("member", new Member());
		
		return "home/formtag/radio/registerFormRadio01";
	}
	
	@RequestMapping(value = "/registerFormRadios02", method = RequestMethod.GET)
	public String registerFormRadios02(Model model) {
		log.info("registerFormRadios02() 실행");
		
		List<CodeLabelValue> genderCodeList = new ArrayList<CodeLabelValue>();
		genderCodeList.add(new CodeLabelValue("01", "Male"));
		genderCodeList.add(new CodeLabelValue("02", "Female"));
		genderCodeList.add(new CodeLabelValue("03", "Other"));
		
		model.addAttribute("genderCodeList", genderCodeList);
		model.addAttribute("member", new Member());
		
		return "home/formtag/radio/registerFormRadio02";
	}
	
	/*
	 * 		10. 라디오 버튼 요소
	 * 			- HTML 라디오 버튼을 출력하려면 <form:radiobutton> 요소를 사용한다.
	 */
	
	@RequestMapping(value = "/registerFormRadio01", method = RequestMethod.GET)
	public String registerFormRadio01(Model model) {
		log.info("registerFormRadio01() 실행");
		
		model.addAttribute("member", new Member());
		
		return "home/formtag/radio/registerFormRadio01";
	}
	
	@RequestMapping(value = "/registerFormRadio02", method = RequestMethod.GET)
	public String registerFormRadio02(Model model) {
		log.info("registerFormRadio02() 실행");
		Member member = new Member();
		member.setGender("Female");
		model.addAttribute("member", member);
		return "home/formtag/radio/registerFormRadio01";
	}
}

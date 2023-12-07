package kr.or.ddit.controller.jsp;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/fmttag")
public class JSPFmttgController {
	/*
	 * 		7. 숫자 및 날짜 포맷팅 처리 태그
	 * 			- 숫자 및 날짜의 포맷팅과 관련된 메소드
	 * 			- 문자열을 숫자로, 문자열을 날짜로 변형하여 사용합니다.
	 * 			
	 * 			1) <fmt:formatNumber>
	 * 				- 숫자를 형식화한다.
	 * 				- value : String of Number : 서식에 맞춰 출력할 숫자
	 * 				- type : String : 어떤 서식으로 출력할지를 정한다.
	 * 				- pattern : String : 직접 숫자를 출력할 서식을 지정한다.
	 * 				- var : String : 포맷팅한 결과를 지정할 변수 이름
	 * 			** type 속성 : number의 경우 숫자 형식으로 , percent의 영우 '%' 형식으로,
	 * 						 currency의 경우 통화 형식을 출력한다.
	 * 						 기본 값은 number, currency는 속해있는 국가 코드에 맞게 통화 형식이 부여
	 * 
	 * 			2) <fmt:parseNumber>
	 * 				- 문자열을 숫자로 변환
	 * 				- value : String : 파싱할 문자열
	 * 	`			- type : String : value 속성의 문자열 타입을 지정
	 * 				- pattern : String : 파싱할 때 직접 사용할 서식을 지정한다.
	 * 				- var : String : 파싱한 결과를 저장할 변수 이름을 지정한다.
	 * 				** type 속성 : number, currency, parcent가 올 수 있다.
	 * 			
	 * 			3) <fmt:formatDate>
	 * 				- Date 객체를 문자열로 변환한다.
	 * 				- value : java.util.Date : 포맷팅할 날짜 및 시작  값
	 * 				- type : String : 날짜, 시간 또는 둘 다 포맷팅 할지의 여부를 지정
	 * 				- dateStyle : String : 날짜에 대해 미리 정의된 포멧팅 스타일을 지정
	 * 				- timeStyle : String : 시간에 대해 미리 정의된 포멧팅 스타일을 지정
	 * 				- pattern : String : 파싱할 때 직접 사용할 서식을 지정한다.
	 * 				- var : String : 파싱한 결과를 저장할 변수 이름을 지정한다.
	 * 				** type 속성 : time, date, both 중 한가지 값을 가질 수 있으며 기본값은 date이다
	 * 				dateStyle : default, short, medium, long, full 중 한가지 값을 가질 수 있으며, 기본값은 default이다.
	 * 				timeStyle : default, short, medium, long, full 중 한가지 값을 가질 수 있으며, 기본값은 default이다.
	 * 
	 * 			3) <fmt:parseDate>
	 * 				- 문자열을 date 객체로 변환
	 * 				- value : String : 파싱할 문자열
	 * 				- type : String : 날짜, 시간 또는 둘 다 포맷팅 할지의 여부를 지정한다.
	 * 				- dateStyle : String : 날짜에 대해 미리 정의된 포멧팅 스타일을 지정
	 * 				- timeStyle : String : 시간에 대해 미리 정의된 포멧팅 스타일을 지정
	 * 				- pattern : String : 파싱할 때 직접 사용할 서식을 지정한다.
	 * 				- var : String : 파싱한 결과를 저장할 변수 이름을 지정한다.
	 * 				** type 속성 : time, date, both 중 한가지 값을 가질 수 있으며 기본값은 date이다
	 * 				dateStyle : default, short, medium, long, full 중 한가지 값을 가질 수 있으며, 기본값은 default이다.
	 * 				timeStyle : default, short, medium, long, full 중 한가지 값을 가질 수 있으며, 기본값은 default이다.
	 */
	
	// 1) type 속성을 지정하거나 pattern 속성을 지정하여 숫자를 형식화한다.
	@RequestMapping(value="/home0101", method=RequestMethod.GET)
	public String home0101(Model model) {
		int coin = 100;
		model.addAttribute("coin", coin);
		return "home/fmttag/home0101";
	}
	
	// 2) type 속성이 지정되지 않으면 기본값은 number이다
	@RequestMapping(value="/home0201", method=RequestMethod.GET)
	public String home0201(Model model) {
		String coin = "1000";
		model.addAttribute("coin", coin);
		return "home/fmttag/home0201";
	}
	
	// 3) type 속성이 currency이다.
	// 만약, type 속성이 percent인 경우에 넘겨받아야할 값이 1000%과 같이 % 스타일 형태 값이 넘어가야한다.
	// 그래서 type 생성과 일치하여 값을 파싱할 수 있다
	@RequestMapping(value="/home0202", method=RequestMethod.GET)
	public String home0202(Model model) {
		String coin = "￦1000";
		model.addAttribute("coin", coin);
		return "home/fmttag/home0202";
	}
	
	// 4) pattern 속성을 사용해서 직접 사용할 서식을 지정한다.	
	@RequestMapping(value="/home0204", method=RequestMethod.GET)
	public String home0204(Model model) {
		String coin = "1.000.25";
		model.addAttribute("coin", coin);
		return "home/fmttag/home0301";
	}
	
	// 6) type 속성을 date로 지정하여 날자 포멧팅을 한다. 
	// 7) type 속성을 time로 지정하여 날자 포멧팅을 한다. 
	// 8) type 속성을 both로 지정하여 날자 포멧팅을 한다. 
	@RequestMapping(value="/home0301", method=RequestMethod.GET)
	public String home0301(Model model) {
		Date date  = new Date();
		model.addAttribute("now", date);
		return "home/fmttag/home0301";
	}
	
	// 9) pattern 속성을 지정하여 날짜 포맷팅
	@RequestMapping(value="/home0304", method=RequestMethod.GET)
	public String home0304(Model model) {
		Date date  = new Date();
		model.addAttribute("now", date);
		return "home/fmttag/home0304";
	}
	
	// 11) dateStyle 속성을 각 스타일로 지정하여 문자열을 Date 객체로 변환한다.
	@RequestMapping(value="/home0402", method=RequestMethod.GET)
	public String home0402(Model model) {
		String dateValueShort = "20. 2. 1";
		String dateValueMedium = "2020. 2. 1";
		String dateValueLong = "2020년 2월 1일 (금)";
		String dateValueFull = "2020년 2월 1일 금요일";
		
		model.addAttribute("dateValueShort", dateValueShort);
		model.addAttribute("dateValueMedium", dateValueMedium);
		model.addAttribute("dateValueLong", dateValueLong);
		model.addAttribute("dateValueFull", dateValueFull);
		
		// 각 dateStyle로 지정한 값이 페이지로 넘어가 parsing이 진행될 때, parseDate 내에 
		// dateStype을 각 값과 일치하느 ㄴ스타일 형태로 지정해주어야 값이 파싱된다.
		return "home/fmttag/home0402";
	}
	
	// 12) pattern 속성을 지정하여 문자열을 Date 객체로 변환한다.
	@RequestMapping(value="/home0403", method=RequestMethod.GET)
	public String home0403(Model model) {
		String dataValue = "2020-02-01 15:00:24";
		model.addAttribute("dataValue", dataValue);
		return "home/fmttag/home0403";
	}
}

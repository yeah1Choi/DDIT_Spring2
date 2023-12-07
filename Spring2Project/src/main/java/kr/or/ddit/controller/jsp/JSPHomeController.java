package kr.or.ddit.controller.jsp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class JSPHomeController {
/*
 * 	[ 7장. JSP ]
 * 		1. 지시자
 * 			- 지시자(directive)는 JSP 페이지에 대한 설정 정보를 지정할 때 사용한다.
 * 			- JSP가 제공하는 지시자에는 page 지시자, taglib 지시자, include 지시자로 세가지가 있다.
 * 			1) page 지시자
 * 				- JSP 페이지에 대한 정보를 지정한다.
 * 					= contentType : JSP가 생성할 문저의 MIME 타입과 문자코드를 지정한다.
 * 					= pgaeEncoding : JSP 페이지 자체의 문자 코드를 지정한다.
 * 					= session : JSP 페이지가 세션을 사용할 지의 여부를 지정한다.
 * 					= import : JSP 페이지에서 사용할 자바 클래스를 지정한다.
 * 			2) taglib 지시자
 * 				- JSP 페이지에서 사용할 태그 라이브러리를 지정한다.
 * 					= prefix : 태그 라이브러리를 호출할 때 사용할 접두어를 지정한다.
 * 					= uri : TLD 파일이 위치한 URI 혹은 파일 경로를 지정한다.
 *			3) include 지시자
 *				- JSP 페이지의 특정 영역에 다른 문서를 포함한다.
 *					= file : 포함할 파일의 경로를 지정한다.
 *		2. 스크립틀릿
 *			- 스크립틀릿(Scriptlet)은 JSP페이지에서 자바코드를 실행할 때 사용하는 코드의 블록이다.
 *			- 스크립틀릿 코드 블록은 <%와 %> 사이, 그리고 <%=와 %>사이의 자바 코드를 포함할 수 있다.
 *			- 현재는 스크립틀릿을 사용하기 보다느 ㄴJSTL과 같은 커스텀 태그 라이브러리와 EL을 조합해서 JSP를 구현하느 ㄴ방법을 많이 사용한다.
 *
 *		3. 커스텀 태그 라이브러리
 *			- 스크립트 요소가 많아지면 많아질수록 JSP 코드는 복잡해진다.
 *			- 이문제르 ㄹ해결하느 ㄴ한 가지 방법은 커스첨 태그를 사용하느 ㄴ것이다.
 *			- 커스텀 태그를 사용하면 뷰를 표시하기 위한 로직을 공통화하거나, 표현하기 복잡한 로직을 캡슐화할 수 있어서
 *			- JSP의 구현 코드를 간결하게 만들 수 있다. 그리고, 커스텀 태그를 모아 놓은 것을 커스텀 태그 라이브러리하고 한다.
 *			
 *			# 대표적인 태그 라이브러리
 *				- JSTL(javaserver pages standard tag library)
 *				- spring-form JSP tag library
 *				- spring JSP tag library
 *				- spring security JSP tag library
 *		
 *		4. 표현언어(EL)
 *			- JSP는 EL(expression language)이라는 표현 언어는 사용해 값의 참조, 출력, 연산을 할 수 있다.
 *			- EL 식은 ${...} 또는 #{...} 형식으로 작성한다.
 *			# EL을 사용하여 객체를 조회하느 ㄴ방법은 다음과 같다.
 *				- 자바빈즈 프로퍼티를 조회하는 셩우 "속성명.프로퍼티명"을 지정한다.
 *				- List나 배열 요소를 조회하는 경우 "속성명[요소위치]"를 지정한다.
 *				- Map요소를 조회하는 경우 "속성명.키명" 또는 "속성명[키명]"을 지정한다..
 *
 *			# 사용가능한 연산자
 *				- EL에서는 다음과 같은 연산자를 사용할 수 있다.
 *					# 산술 연산자
 *						+ 더하기 , - 빼기, * 곱하기, /(div) 나누기, %(mood) 나머지
 *					# 비교 연산자
 *						==(eq) : 같은 값인지 비교
 *						!=(ne) : 다른 값인지 비교
 *						<=(le) : 왼쪽이 작거나 같은 값인지 비교
 *						>=(ge) : 왼쪽이 크거나 같은 값인지 비교
 *						<(lt) : 왼쪽이 작은 값인지 비교
 *						>(gt) : 왼쪽이 큰 값인지 비교
 * 					# empty 연산자
 * 						- null이거나 공백(문자열의 경우 공백 문자)인지 비교
 * 							[true 조건 :::]
 * 								- null 값, 빈 문자열(""), 길이가 0인 배열, 빈 Collection
 * 					# 논리 연산자
 * 						- &&(and) : 두 피연산자 모두 true면 bool true를 반환하고, 그렇지 않으면 false 반환
 * 						- ||(or) : 두 피연산자 중 하나 또는 모두 true면 bool true를 반환하고, 그렇지 않으면 false 반환
 * 						- !(not) : 해당 피연산자의 의미를 반대로 바꾼다
 */
	
	@RequestMapping(value="/home0104", method=RequestMethod.GET)
	public String home0104(Model model) {
		log.info("home0104() 실행");
		
		Map<String, String> memberMap = new HashMap<String, String>();
		memberMap.put("userId", "hong");
		memberMap.put("password", "1234");
		memberMap.put("email", "aaa@ccc.com");
		memberMap.put("userName", "홍길동");
		
		model.addAttribute("memberMap", memberMap);
		return "home/home0104";
	}
	
	// 산술 연산자
	@RequestMapping(value="/home0201", method=RequestMethod.GET)
	public String home0201(Model model) {
		int coin = 100;
		model.addAttribute("coin", coin);
		return "home/home0201";
	}
	
	// 비교 연산자
	@RequestMapping(value="/home0202", method=RequestMethod.GET)
	public String home0202(Model model) {
		int coin = 1000;
		String userId = "hongkd";
		
		model.addAttribute("coin", coin);
		model.addAttribute("userId", userId);
		
		return "home/home0202";
	}
	
	@RequestMapping(value="/home0302", method=RequestMethod.GET)
	public String home0302(Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		return "home/home0302";
	}
	
	// 논리 연산자
	@RequestMapping(value="/home0401", method=RequestMethod.GET)
	public String home0401(Model model) {
		int coin = 1000;
		String userId = "hongkd";
		
		Member member = new Member();
		model.addAttribute("member", member);
		model.addAttribute("coin", coin);
		model.addAttribute("userId", userId);
		
		return "home/home0401";
	}
}

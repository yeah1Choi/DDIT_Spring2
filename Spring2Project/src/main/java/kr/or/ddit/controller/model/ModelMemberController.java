package kr.or.ddit.controller.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ModelMemberController {
	
	/*
	 * 	[ 6장. 데이터 전달자 모델 ]
	 * 
	 * 	1. 모델 객체
	 * 		- Model 객체는 뷰(view)에 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할을 한다
	 * 
	 * 	2. 모델을 통한 데이터 전닿
	 * 		- Model 객체를 통해서 다양한 데이터를 뷰에 전달할 수 있다.
	 * 		** 우리가 사용해본 데이터 전닿자
	 * 			ModelAndView
	 * 			Model
	 */
	
	// 1) 매개변수에 선언된 모델 객체의 addAttribute() 메서드를 호출하여 데이터를 뷰에 전달한다.
	@RequestMapping(value="/read01", method = RequestMethod.GET)
	public String read01(Model model) {
		log.info("read01() 실행");
		
		model.addAttribute("userId", "choiyw");
		model.addAttribute("password", "1234");
		model.addAttribute("email", "choiyw@gmail.com");
		model.addAttribute("userName", "홍길동");
		model.addAttribute("birthDay", "1999-09-09");
		
		return "member/read01";
	}
	
	// 2) Model 객체에 자바빈즈 클래스 객체를 값으로만 전달할 때는 뷰에서 참조할 이름을 클래스명의 앞글자를 소문자로 변환하여 처리한다.
	@RequestMapping(value="/read02", method = RequestMethod.GET)
	public String read02(Model model) {
		log.info("read02() 실행");
		
		Member member = new Member();
		member.setUserId("Choiyw");
		member.setPassword("1234");
		member.setEmail("Choiyw@gmail.com");
		member.setUserName("홍길동");
		member.setBirthDay("1999-09-09");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1999);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 10);

		member.setDateOfBirth(cal.getTime());
		model.addAttribute(member);
		
		return "member/read02";
	}
	
	// 3) Model 객체에 자바빈즈 클래스 객체를 특정한 이름을 지정하여 전달할 수 있다.
	@RequestMapping(value="/read03", method = RequestMethod.GET)
	public String read03(Model model) {
		log.info("read03() 실행");
		
		Member member = new Member();
		member.setUserId("Choiyw");
		member.setPassword("1234");
		member.setEmail("Choiyw@gmail.com");
		member.setUserName("홍길동");
		member.setBirthDay("1999-09-09");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1999);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 10);

		member.setDateOfBirth(cal.getTime());
		model.addAttribute("user", member);
		
		return "member/read03";
	}
	
	// 4) Model 객체를 통해 배열과 컬렉션 객체를 전달할 수 있다.
	@RequestMapping(value="/read04", method = RequestMethod.GET)
	public String read04(Model model) {
		log.info("read04() 실행");
		
		String[] carArray = {"jeep", "bmw"};
		
		List<String> carList = new ArrayList<String>();
		carList.add("jeep");
		carList.add("volvo");
		
		String[] hobbyArray = {"Music", "Movie"};
		
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("Sport");
		hobbyList.add("Read");
		
		model.addAttribute("carArray", carArray);
		model.addAttribute("carList", carList);
		model.addAttribute("hobbyArray", hobbyArray);
		model.addAttribute("hobbyList", hobbyList);
		
		return "member/read04";
	}
	
	// 5) Model 객체를 통해 다양한 타입의 값을 전달할 수 있다.
	@RequestMapping(value="/read05", method = RequestMethod.GET)
	public String read05(Model model) {
		log.info("read05() 실행");
		
		Member member = new Member();
		member.setUserId("Choiyw");
		member.setPassword("1234");
		member.setEmail("Choiyw1234@gmail.com");
		member.setUserName("홍길동");
		member.setBirthDay("1999-09-09");
		member.setGender("남자");
		member.setDeveloper("Y");
		member.setForeinger(true);
		member.setNationality("Korea");
		member.setCars("Jeep");
		
		String[] carArray = {"jeep", "bmw"};
		member.setCarArray(carArray);
		
		List<String> carList = new ArrayList<String>();
		carList.add("audi");
		carList.add("volvo");
		member.setCarList(carList);
		
		String[] hobbyArray = {"Music, Read"};
		member.setHobbyArray(hobbyArray);
		
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("Sport");
		hobbyList.add("Dance");
		member.setHobby("Sport, Dance");
		
		Address address = new Address();
		address.setPostCode("123456");
		address.setLocation("Daejeon");
		member.setAddress(address);
		
		List<Card> cardList = new ArrayList<Card>();
		Card card1 = new Card();
		card1.setNo("12345");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2023);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 20);
		card1.setValidMonth(cal.getTime());
		cardList.add(card1);
		
		Card card2 = new Card();
		card2.setNo("98765");
		
		cal.set(Calendar.YEAR, 2023);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 20);
		card2.setValidMonth(cal.getTime());
		cardList.add(card2);
		
		member.setCardList(cardList);
		member.setDateOfBirth(cal.getTime());
		member.setIntroduction("반갑습니다");
		model.addAttribute("user", member);
		return "member/read05";
	}
}

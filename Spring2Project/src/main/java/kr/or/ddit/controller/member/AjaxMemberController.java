package kr.or.ddit.controller.member;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ajax")
public class AjaxMemberController {
	/*
	 * 		9. Ajax 방식 요청 처리
	 */
	
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String ajaxRegisterForm() {
		log.info("ajaxRegisterForm() 실행");
		return "member/ajaxRegisterForm";
	}
	
	// 3) 객체 타입의 JSON 요청 데이터 @RequestBody 어노테이션을 지정하여 자바빈즈 매개변수로 처리한다.
	@ResponseBody
	@RequestMapping(value="/register03", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister03(@RequestBody Member member){
		log.info("ajaxRegister03() 실행");
		log.info("userId : "+member.getUserId());
		log.info("password : "+member.getPassword());
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 4) 객체 타입의 JSON 요청 데이터는 문자열 매개변수로 처리할 수 없다.
	@ResponseBody
	@RequestMapping(value="/register04", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister04(String userId, String password){
		// 요청 본문에서 데이터가 바인딩 되지 않아 userId가 null로 나온다.
		// 요청 본문에서 데이터를 가져오려면 @RequestBody 어노테이션을 꼭 붙어야한다.
		// 그래서 단일 데이터를 컨트롤러 메서드에서 값으로 받기 위해선 기본 타입 변수를 사용하지 않고
		// 컬렉션 Map을 활용하도록 합니다(이때에도 @RequestBody 필수 ~)
		log.info("ajaxRegister04() 실행");
		log.info("userId : "+userId);
		log.info("password : "+password);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 5) 요청 URL에 쿼리 파라미터를 붙여서 전달하면 문자열 매개변수로 처리한다.
	@ResponseBody
	@RequestMapping(value="/register05", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister05(String userId, String password){
		log.info("ajaxRegister05() 실행");
		log.info("userId : "+userId);
		log.info("password : "+password);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 7) 객체 배열 타입의 JSON 요청 데이터를 자바빈즈 요소를 가진 리스트 컬렌션 매개변수에 @RequstBody 어노테이션을 지정하여 처리한다
	@ResponseBody
	@RequestMapping(value="/register07", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister07(
			@RequestBody List<Member> memberList
			){
		// 비동기 처리시 , List 컬렉션으로 데이터를 받을 때에는 @RequestBody라는 어노테이션을 이용하여 바인딩한다.
		// 동기 처리시, 컨트롤러 메서드 내에서 List타입으로 값을 바인딩할 수 없지만, 객체 내 존재하는 List 타입으로는 데이터를 바인딩할 수 있다.
		// 동기와 비동기 처리시의 차이점 꼭 기억!
		log.info("ajaxRegister07() 실행");
		for(Member member : memberList) {
			log.info("member.userId : "+member.getUserId());
			log.info("member.password : "+member.getPassword());
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 8) 중첩된 객체 차입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.
	@ResponseBody
	@RequestMapping(value="/register08", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister08(
			@RequestBody Member member
			){
		log.info("ajaxRegister08() 실행");
		
		log.info("userId : "+member.getUserId());
		log.info("usepasswordrId : "+member.getPassword());
		
		Address address = member.getAddress();
		if(address != null) {
			log.info("address.postCode : "+address.getPostCode());
			log.info("address.location : "+address.getLocation());
		} else {
			log.info("address is null");
		}
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 9) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 컬렉션 리스트 매개변수로 처리한다.(객체 안에 들어있는 리스트 변수
	@ResponseBody
	@RequestMapping(value="/register09", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister09(
			@RequestBody Member member
			){
		log.info("ajaxRegister0() 실행");
		
		log.info("userId : "+member.getUserId());
		log.info("usepasswordrId : "+member.getPassword());
		
		List<Card> cardList = member.getCardList();
		
		if(cardList != null && cardList.size() > 0) {
			log.info("cardList.size() : "+cardList.size());
			for(Card card : cardList) {
				log.info("card.no : "+card.getNo());
				log.info("card.validMonth : "+card.getValidMonth());
			}
		} else {
			log.info("cardList is null");
		}
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
}

package kr.or.ddit.controller.crud.notice;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.crud.NoticeMemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeLoginController {

	@Inject
	private INoticeService noticeService;

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String noticeLogin(Model model) {
		model.addAttribute("bodyText", "login-page");
		return "conn/login";
	}

	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest req, NoticeMemberVO member, Model model, RedirectAttributes ra) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();

		if (StringUtils.isBlank(member.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요 !");
		}
		if (StringUtils.isBlank(member.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요 !");
		}

		if (errors.size() > 0) { // 에러정보가 존재
			model.addAttribute("errors", errors);
			model.addAttribute("member", member);
			model.addAttribute("bodyText", "login-page");
			goPage = "conn/login";
		} else { // 정상적인 데이터
			NoticeMemberVO memberVO = noticeService.loginCheck(member);
			if (memberVO != null) { // 로그인 성공
				HttpSession session = req.getSession();
				session.setAttribute("SessionInfo", memberVO);
				ra.addFlashAttribute("message", "로그인 성공 !");
				goPage = "redirect:/notice/list.do";
			} else { // 로그인 실패
				model.addAttribute("message", "서버에러, 로그인 정보를 정확하게 확인해주세요 !");
				model.addAttribute("member", member);
				model.addAttribute("bodyText", "login-page");
				goPage = "conn/login";
			}
		}
		return goPage;
	}

	// 아이디 중복 체크를 확인하는 메소드로 주소가 새로고침이 없이 이루어져야하는 기능이기 때문에 비동기 ajax에 담겨 컨트롤러로 넘어온 데이터
	// ajax에 담겨온 데이터는 memId 하나다. <- 단일 데이터
	// ResponseEntity : 다시 비동기(ajax)로 JSON 형식으로 HTTP 응답을 반환할 때 응답의 상태 코드와 함께 반환하기 위해
	// 사용
	// -> 서비스 결과와 OK HTTP 상태를 포함하는 ResponseEntity<ServiceResult>를 반환함
	@ResponseBody
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public ResponseEntity<ServiceResult> idCheck(@RequestBody Map<String, String> map) {
		/*
		 * 단일 데이터를 꺼낼 때 0) ajax 설정에서 ContentType 설정을 하지 않고, 데이터만 {memId : id} 설정해서 넘길 때
		 * - String memId로 꺼낼 수 있다. > 이 형태는 쿼리 스트링에 구성된 memId를 꺼낼 때도 동일함 1) ajax 설정에서
		 * ConetentType 설정을 하지 않고, 데이터만 JSON.stringify()일 때 - @RequestBody로 String
		 * memId를 꺼내면 '%7B%22memId%22%3A...=' 이런 데이터가 넘어옴
		 * 
		 * 2) ajax 설정에서 ConetentType 설정을 하고, 데이터만 JSON.stringify()일 때(데이터 json 객체로 넘어감)
		 * - @RequestBody로 String memId를 꺼내면 '{memId : a001}' 데이터가 넘어옴
		 * 
		 * 3) ajax 설정에서 ConetentType 설정을 하고, 데이터만 JSON.stringify()일 때(데이터 json 객체로 넘어감)
		 * - @RequestParam로 String memId를 꺼내면 400에러가 발생한다. - 데이터가 json형식으로 넘어오는 것을
		 * param으로 꺼내는 건 할 수 없음
		 * 
		 * 4) ajax 설정에서 ConetentType 설정을 하고, 데이터만 JSON.stringify()일 때(데이터 json 객체로 넘어감)
		 * - @RequestBody Map<String, String> map을 꺼내면 'a001' 데이터가 넘어옴 (**지금 이 형식 사용)
		 * 
		 * 우리는 단일 데이터를 받아낼 때에 고민하지말고 컬렉션 Map또는 단일 데이터를 받을 VO 객체를 파라미터로 설정하여 값을 받는다.
		 * 
		 * @RequestBody : 메서드의 반환 값이 직접 응답 본문으로 직렬화되어야 함 = 비동기로 요청 본문 전체가 와서 처리되어야하기 때문에
		 * 사용 = 요청 본문(request body)에 담긴 값(http요청의 본문이 그대로 전달)을 자바객체로 변환
		 * 
		 */
		log.info("넘겨받은 아이디 : " + map.get("memId"));
		ServiceResult result = noticeService.idCheck(map.get("memId"));

		return new ResponseEntity<ServiceResult>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/signup.do", method = RequestMethod.GET)
	public String signupForm(Model model) {
		model.addAttribute("bodyText", "register-page");
		return "conn/register";
	}

	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String singup(HttpServletRequest req, NoticeMemberVO memberVO, Model model, RedirectAttributes ra) {
		String goPage = "";

		Map<String, String> errors = new HashMap<String, String>();
		if (StringUtils.isBlank(memberVO.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요.");
		}
		if (StringUtils.isBlank(memberVO.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요.");
		}
		if (StringUtils.isBlank(memberVO.getMemName())) {
			errors.put("memName", "이름를 입력해주세요.");
		}

		if (errors.size() > 0) { // 넘겹받은 데이터의 에러가 존재
			model.addAttribute("errors", errors);
			model.addAttribute("member", memberVO);
			model.addAttribute("bodyText", "register-page");
			return "conn/register";
		} else { // 정상적인 데이터를 받았을 때
			ServiceResult result = noticeService.signup(req, memberVO);
			if (result.equals(ServiceResult.OK)) { // 가입 성공
				ra.addFlashAttribute("message", "회원가입을 완료했습니다.");
				goPage = "redirect:/notice/login.do";
			} else { // 가입 실패
				model.addAttribute("message", "서버에러, 다시 시도해주세요.");
				model.addAttribute("member", memberVO);
				model.addAttribute("bodyText", "register-page");
				goPage = "conn/register";
			}
		}
		return goPage;
	}

	@RequestMapping(value = "/forget.do", method = RequestMethod.GET)
	public String loginForgetIdAndPw(Model model) {
		model.addAttribute("bodyText", "login-page");
		return "conn/forget";
	}

	@ResponseBody
	@RequestMapping(value = "/findId.do", method = RequestMethod.POST)
	public ResponseEntity<NoticeMemberVO> findId(@RequestBody Map<String, String> map) {
		log.info("넘겨받은 이메일 : " + map.get("memEmail"));
		log.info("넘겨받은 이름 : " + map.get("memName"));

		NoticeMemberVO memberVO = new NoticeMemberVO();
		memberVO.setMemEmail(map.get("memEmail"));
		memberVO.setMemName(map.get("memName"));
		
		NoticeMemberVO memberResult = noticeService.findId(memberVO);
		
		return new ResponseEntity<NoticeMemberVO>(memberResult, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findPw.do", method = RequestMethod.POST)
	public ResponseEntity<NoticeMemberVO> findPw(@RequestBody Map<String, String> map) {
		log.info("넘겨받은 아이디 : " + map.get("memId"));
		log.info("넘겨받은 이메일 : " + map.get("memEmail"));
		log.info("넘겨받은 이름 : " + map.get("memName"));
		
		NoticeMemberVO memberVO = new NoticeMemberVO();
		memberVO.setMemId(map.get("memId"));
		memberVO.setMemEmail(map.get("memEmail"));
		memberVO.setMemName(map.get("memName"));
		
		NoticeMemberVO memberResult = noticeService.findPw(memberVO);
		
//		ServiceResult status = null;
//		if(memberResult == null || memberResult.equals("")) {
//			status = ServiceResult.EXIST;
//		} else {
//			status = ServiceResult.NOTEXIST;
//		}
		
		return new ResponseEntity<NoticeMemberVO>(memberResult, HttpStatus.OK);
	}
}

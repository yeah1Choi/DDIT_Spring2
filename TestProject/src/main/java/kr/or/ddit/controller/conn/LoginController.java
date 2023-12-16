package kr.or.ddit.controller.conn;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
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
import kr.or.ddit.service.ILoginService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginController {
	
	@Inject
	private ILoginService loginService;
	
	@RequestMapping(value="/signin.do", method = RequestMethod.GET)
	public String signIn() {
		return "conn/signin";
	}
	
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(HttpSession session, MemberVO memberVO, Model model, RedirectAttributes ra) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(memberVO.getMemId())){
			errors.put("memId", "아이디를 입력해주세요.");
		}
		if(StringUtils.isBlank(memberVO.getMemPw())){
			errors.put("memPw", "비밀번호를 입력해주세요.");
		}
		
		if(errors.size() > 0) { // 에러가 있음
			model.addAttribute("errors", errors);
			model.addAttribute("memberVO", memberVO);
			goPage = "conn/signin";
		} else {
			MemberVO member = loginService.loginCheck(memberVO);
			if(member != null) {
				session.setAttribute("SessionInfo", member);
				ra.addFlashAttribute("message", member.getMemName() + "님, 환영합니다.");
				goPage = "redirect:/board/list.do";
			} else {
				model.addAttribute("message", "서버에러, 다시 시도해주세요");
				model.addAttribute("memberVO", member);
				goPage = "conn/signin";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value="/signup.do", method = RequestMethod.GET)
	public String signUpForm() {
		return "conn/signup";
	}
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String signup(MemberVO member, Model model, RedirectAttributes ra) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(member.getMemId())){
			errors.put("memId", "아이디를 입력해주세요.");
		}
		if(StringUtils.isBlank(member.getMemPw())){
			errors.put("memPw", "비밀번호를 입력해주세요.");
		}
		if(StringUtils.isBlank(member.getMemName())){
			errors.put("memName", "이름을 입력해주세요.");
		}
		if(StringUtils.isBlank(member.getMemNickname())){
			errors.put("memNickname", "닉네임을 입력해주세요.");
		}
		
		if(errors.size() > 0) { // 에러가 있음
			model.addAttribute("errors", errors);
			model.addAttribute("memberVO", member);
			goPage = "conn/signup";	// tiles의 definition으로 설정되어있는 name 속성의 값과 매핑된다.
		} else {
			ServiceResult result = loginService.signup(member);
			if(result.equals(ServiceResult.OK)) {	// 등록 성공
				ra.addFlashAttribute("message", "회원가입이 완료되었습니다.");
				goPage = "redirect:/signin.do";
			} else { // 등록 실패
				model.addAttribute("message", "서버에러, 다시 시도해주세요");
				model.addAttribute("memberVO", member);
				goPage = "conn/signup";
			}
		}
		return goPage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> idCheck(@RequestBody Map<String, String> map){
		ServiceResult result = loginService.idCheck(map.get("memId"));
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/nickNameCheck.do", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> nickNameCheck(@RequestBody Map<String, String> map){
		ServiceResult result = loginService.nickNameCheck(map.get("memNickname"));
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
}

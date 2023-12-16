package kr.or.ddit.controller;



import java.security.Principal;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.CrudMember;
import kr.or.ddit.vo.CustomUser;

@Controller
@RequestMapping("/board")
public class BoardContoller {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		log.info("list : access to all");
		return "board/list";
	}
	
	// 회원권한을 가진 사용자만 접근 가능
	@PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')")
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Principal principal) {
		log.info("register : access to member");
		
		// 사용자 정보 가져오기
		// [방법1] principal 객체를 받아와서 인증된 정보 가져오기
		log.info("name : " + principal.getName());
		
		// [방법2] User 객체 정보 얻어와서 사용
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("username : " + user.getUsername());
		log.info("password : " + user.getPassword());
		CrudMember member = user.getMember();
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		
		Iterator<GrantedAuthority> ite = user.getAuthorities().iterator();
		while(ite.hasNext()) {
			log.info("auth : " + ite.next().getAuthority());
		}
		
		return "board/register";
	}
}

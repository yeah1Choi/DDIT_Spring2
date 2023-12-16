package kr.or.ddit.security;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.or.ddit.mapper.IMemberMapper;
import kr.or.ddit.vo.CrudMember;
import kr.or.ddit.vo.CustomUser;

public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Inject
	private BCryptPasswordEncoder bpe;
	
	@Inject
	private IMemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername() 실행 !");

		String password = "1234";
		log.info("## 암호화된 비밀번호 : " + bpe.encode(password));
		log.info("## 암호화된 비밀번호 : " + bpe.encode(password));
		log.info("## 암호화된 비밀번호 : " + bpe.encode(password));
		log.info("## 암호화된 비밀번호 : " + bpe.encode(password));
		log.info("## 암호화된 비밀번호 : " + bpe.encode(password));
		log.info("## 암호화된 비밀번호 : " + bpe.encode(password));
		log.info("## 암호화된 비밀번호 : " + bpe.encode(password));
		
		log.info("Load User By Username : " + username);
		
		// UserDetailsService를 등록하는 과정에서 우리가 할 목표는 User 객체의 정보와
		// 인증되어 실제로 사용될 내 id에 해당하는 회원정보를 CrudMember에 담고 그것을 UserDetails 정보 안에서
		// 가용할 수 있도록 만든다.
		
		CrudMember member;
		
		try {
			member = memberMapper.readByUserId(username);
			log.info("queried by member mapper : " + member);
			
			return member == null ? null : new CustomUser(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

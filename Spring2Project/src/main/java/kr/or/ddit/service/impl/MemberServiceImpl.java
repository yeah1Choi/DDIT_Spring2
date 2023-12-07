package kr.or.ddit.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.IMemberMapper;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.vo.crud.CrudMember;
import kr.or.ddit.vo.crud.CrudMemberAuth;

@Service
public class MemberServiceImpl implements IMemberService {
	
	@Inject
	private IMemberMapper mapper;

	@Transactional
	@Override
	public void register(CrudMember member) {
		// 회원 1명의 정보를 등록 시, 하나의 권한을 가질 수 있다.
		mapper.create(member);	// 회원 정보 1명의 데이터를 등록
		
		// 등록된 회원정보를 이용해서 권한을 등록
		CrudMemberAuth memberAuth = new CrudMemberAuth();
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_USER");
		
		if(true) {
			throw new NullPointerException();
		}
		
		mapper.createAuth(memberAuth);
		
	}

	@Override
	public List<CrudMember> list() {
		return mapper.list();
	}

	@Override
	public CrudMember read(int userNo) {
		return mapper.read(userNo);
	}

	@Override
	public void modify(CrudMember member) {
		// 1. 일반 회원에 대한 데이터를 수정
		mapper.modify(member);
		
		// 2. 권한을 수정
		// 	  -> 회원번호에 대한 권한 모두를 삭제 
		int userNo = member.getUserNo();	
		mapper.deleteAuth(userNo);
		
		// 	  -> 새롭게 수정될 권한들을 insert
		List<CrudMemberAuth> authList = member.getAuthList();
		for(int i =0;i<authList.size();i++) {
			CrudMemberAuth memberAuth = authList.get(i);
			String auth = memberAuth.getAuth();
			
			if(auth == null) {
				continue;
			}
			if(auth.trim().length() == 0) {
				continue;
			}
			memberAuth.setUserNo(userNo);
			mapper.createAuth(memberAuth);
		}
	}

	@Override
	public void remove(int userNo) {
		// 1. 권한 먼저 삭제
		mapper.deleteAuth(userNo);
		
		// 2. 다음에 회원 정보를 삭제
		mapper.delete(userNo);
	}

}
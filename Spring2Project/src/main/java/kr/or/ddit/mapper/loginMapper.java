package kr.or.ddit.mapper;

import kr.or.ddit.vo.crud.NoticeMemberVO;

public interface loginMapper {

	public NoticeMemberVO loginCheck(NoticeMemberVO member);

	public NoticeMemberVO idCheck(String memId);

	public int signup(NoticeMemberVO memberVO);

	public NoticeMemberVO findId(NoticeMemberVO memberVO);

	public NoticeMemberVO findPw(NoticeMemberVO memberVO);

}

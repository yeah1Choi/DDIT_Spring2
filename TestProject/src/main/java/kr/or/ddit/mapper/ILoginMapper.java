package kr.or.ddit.mapper;

import kr.or.ddit.vo.MemberVO;

public interface ILoginMapper {

	public MemberVO idCheck(String memId);

	public MemberVO nickNameCheck(String nickname);

	public int signup(MemberVO member);

	public MemberVO loginCheck(MemberVO memberVO);


}

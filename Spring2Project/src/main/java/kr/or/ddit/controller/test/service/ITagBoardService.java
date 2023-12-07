package kr.or.ddit.controller.test.service;

import java.util.List;

import kr.or.ddit.vo.test.TagBoardVO;
import kr.or.ddit.vo.test.TagVO;

public interface ITagBoardService {

	public void register(TagBoardVO tagBoard);

	public List<TagBoardVO> list();

	public TagBoardVO detail(int boNo);

	public void delete(int boNo);

	public void modify(TagBoardVO tagBoard);

	public List<TagVO> readTag(int boNo);

}

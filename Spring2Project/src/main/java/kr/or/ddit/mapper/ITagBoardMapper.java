package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.test.TagBoardVO;
import kr.or.ddit.vo.test.TagVO;

public interface ITagBoardMapper {

	public void create(TagBoardVO tagBoard);

	public void createTag(TagVO tagVO);

	public List<TagBoardVO> list();

	public TagBoardVO detail(int boNo);

	public void delete(int boNo);

	public void deleteTag(int boNo);

	public void modify(TagBoardVO tagBoard);

	public void countHit(int boNo);

	public List<TagVO> readTag(int boNo);

}

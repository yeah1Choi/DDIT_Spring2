package kr.or.ddit.vo.test;

import java.util.List;

import lombok.Data;

@Data
public class TagBoardVO {
	private int boNo;
	private String boTitle;
	private String boContent;
	private String boWriter;
	private String boDate;
	private int boHit;
	private List<TagVO> tagList;
	private String tag;
	
	private String searchType;
	private String searchWord;
}

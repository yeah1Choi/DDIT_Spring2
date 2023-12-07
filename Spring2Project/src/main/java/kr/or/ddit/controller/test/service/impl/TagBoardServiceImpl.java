package kr.or.ddit.controller.test.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.controller.test.service.ITagBoardService;
import kr.or.ddit.mapper.ITagBoardMapper;
import kr.or.ddit.vo.test.TagBoardVO;
import kr.or.ddit.vo.test.TagVO;

@Service
public class TagBoardServiceImpl implements ITagBoardService {

	@Inject
	private ITagBoardMapper mapper;

	@Override
	public void register(TagBoardVO tagBoard) {
		String tagstr = tagBoard.getTag();
		System.out.println("tagstr : " + tagstr);
		String[] tagArr = tagstr.split(" ");

		mapper.create(tagBoard);

		for (int i = 0; i < tagArr.length; i++) {
			String tagName = tagArr[i];

			System.out.println("tag : " + tagName);
			TagVO tagVO = new TagVO();

			if (tagName == null) {
				continue;
			}
			if (tagName.trim().length() == 0) {
				continue;
			}

			tagVO.setBoNo(tagBoard.getBoNo());
			tagVO.setTagName(tagName);
			mapper.createTag(tagVO);
		}
	}

	@Override
	public List<TagBoardVO> list() {
		return mapper.list();
	}

	@Override
	public TagBoardVO detail(int boNo) {
		mapper.countHit(boNo);
		
		return mapper.detail(boNo);
	}

	@Override
	public void delete(int boNo) {
		mapper.deleteTag(boNo);
		mapper.delete(boNo);
	}

	@Override
	public void modify(TagBoardVO tagBoard) {
		
		int boNo = tagBoard.getBoNo();
		mapper.deleteTag(boNo);

		String tagstr = tagBoard.getTag();
		System.out.println("tagstr : " + tagstr);
		String[] tagArr = tagstr.split(" ");
		
		for (int i = 0; i < tagArr.length; i++) {
			String tagName = tagArr[i];

			if (tagName == null) {
				continue;
			}
			if (tagName.trim().length() == 0) {
				continue;
			}
			
			TagVO tag = new TagVO();
			
			tag.setBoNo(boNo);
			tag.setTagName(tagName);
			mapper.createTag(tag);
		}
		
		mapper.modify(tagBoard);
	}

	@Override
	public List<TagVO> readTag(int boNo) {
		List<TagVO> tagList = mapper.readTag(boNo);
		return tagList;
	}

}
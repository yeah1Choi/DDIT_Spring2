package kr.or.ddit.controller.test.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.controller.test.service.ITagBoardService;
import kr.or.ddit.vo.Board;
import kr.or.ddit.vo.crud.CrudMember;
import kr.or.ddit.vo.test.TagBoardVO;
import kr.or.ddit.vo.test.TagVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/test")
public class TagBoardController {

	@Inject
	private ITagBoardService service;
	
	// 태그 게시판 목록 
	@RequestMapping(value="/list.do")
	public String tagList(
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		log.info("tagList() 실행");
		
		TagBoardVO tagBoardVO = new TagBoardVO();
		
		// 검색 기능 
		if(StringUtils.isNotBlank(searchWord)) {
			tagBoardVO.setSearchType(searchType);
			tagBoardVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		List<TagBoardVO> tagBoardList = service.list();
		model.addAttribute("tagBoardList", tagBoardList);
		
		return "test/list";
	}
	
	// 등록 화면 (register)
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String tagForm() {
		return "test/form";
	}
	
	// 등록 기능
	@RequestMapping(value="/form.do", method = RequestMethod.POST)
	public String register(TagBoardVO tagBoard, Model model, RedirectAttributes ra) {
		log.info("crudMemberRegister() 실행");

		String goPage = "";
		service.register(tagBoard);
		
		if(tagBoard == null || tagBoard.equals("")) {	// 등록 실패
			ra.addFlashAttribute("message", "게시글 등록에 실패했습니다. 다시 시도해주십시오.");
			goPage = "redirect:/test/form.do";
		} else {	// 등록 성공
			goPage = "redirect:/test/detail.do?boNo=" + tagBoard.getBoNo();
			ra.addFlashAttribute("message", "게시글 등록을 완료했습니다.");
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String tagDetail(int boNo, Model model) {
		log.info("tagDetail() 실행");
		
		TagBoardVO tagBoard = service.detail(boNo);
		List<TagVO> tagList = service.readTag(boNo);
		model.addAttribute("tagBoard", tagBoard);
		model.addAttribute("tagList", tagList);
		
		return "test/detail";
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String delete(int boNo, Model model) {
		log.info("delete() 실행");
		
		service.delete(boNo);
		model.addAttribute("msg", "삭제 성공했습니다 !");
		return "test/list";
	}
	
	@RequestMapping(value="/modify.do", method = RequestMethod.GET)
	public String update(int boNo, Model model) {
		log.info("update() 실행");
		
		TagBoardVO tagBoard = service.detail(boNo);
		List<TagVO> tagList = service.readTag(boNo);
		model.addAttribute("tagBoard", tagBoard);
		model.addAttribute("tagList", tagList);
		
		return "test/modify";
	}
	
	@RequestMapping(value="/modify.do", method = RequestMethod.POST)
	public String updateForm(TagBoardVO tagBoard, Model model) {
		log.info("updateForm() 실행");
		
		service.modify(tagBoard);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		
		return "test/list";
	}
}

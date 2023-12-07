package kr.or.ddit.controller.crud.notice;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.crud.NoticeVO;
import kr.or.ddit.vo.crud.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeRetreiveController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/list.do")
	public String noticeList(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model
			) {
		PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
		
		// 검색 기능 추가
		// 검색 했을 때의 조건은 키워드(searchWord)가 넘어왔을 때 정확하게 검색을 진행한거니까
		// 이때, 검색을 진행하기 위한 타입과 키워드를  PaginationInfoVO에 셋팅하고 목록을 조회하기 위한 조건으로 
		// 쿼리를 조회할 수 있도록 보내준다.
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		// 현재 페이지 전달 후, start/endRow와 start/endPage 설정
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = noticeService.selectNoticeCount(pagingVO);	// 총 게시글 수 가져오기
		pagingVO.setTotalRecord(totalRecord);
		List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "notice/list";	
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String noticeDetail(int boNo, Model model) {
		NoticeVO noticeVO = noticeService.selectNotice(boNo);
		log.info("noticeVO : " + noticeVO);
		model.addAttribute("notice", noticeVO);
		return "notice/detail";	
	}
	
	
}

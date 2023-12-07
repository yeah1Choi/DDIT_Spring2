package kr.or.ddit.controller.crud.notice;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.crud.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeModifyController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String noticeUpdateForm(int boNo, Model model) {
		NoticeVO noticeVO = noticeService.selectNotice(boNo);
		
		model.addAttribute("notice", noticeVO);
		model.addAttribute("status", "u");	// '수정입니다'  flag
		
		return "notice/form";
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String noticeUpdate(
			HttpServletRequest req,
			NoticeVO noticeVO, Model model, RedirectAttributes ra) {
		String goPage = "";
		
		ServiceResult result = noticeService.updateNotice(req, noticeVO);
		if(result.equals(ServiceResult.OK)) {	// 수정 성공
			goPage = "redirect:/notice/detail.do?boNo="+noticeVO.getBoNo();
			ra.addFlashAttribute("message", "게시글 수정이 완료되었습니다 !");
		} else {	// 수정 실패
			model.addAttribute("notice", noticeVO);
			model.addAttribute("message", "게시글 수정이 실패하였습니다 ..");
			model.addAttribute("status", "u");
			goPage = "notice/form";
		}
		
		return goPage;
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String noticeDelete(
			HttpServletRequest req,
			int boNo, Model model, RedirectAttributes ra) {
		String goPage = "";
		
		ServiceResult result = noticeService.deleteNotice(req, boNo);
		if(result.equals(ServiceResult.OK)) {	// 수정 성공
			goPage = "redirect:/notice/list.do";
			ra.addFlashAttribute("message", "게시글 삭제가 완료되었습니다 !");
		} else {	// 수정 실패
			goPage = "redirect:/notice/detail.do?boNo="+boNo;
			ra.addFlashAttribute("message", "게시글 삭제를 실패하였습니다 ..");
		}
//		addFlashAttribute는 redirect 통해서만 사용가능
		
		return goPage;
	}
}
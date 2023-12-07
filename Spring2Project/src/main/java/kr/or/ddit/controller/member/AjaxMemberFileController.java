package kr.or.ddit.controller.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ajax")
public class AjaxMemberFileController {
	/*
	 * 		10. 파일 업로드 Ajax 방식 요청 처리
	 */
	
	// 파일 업로드 Ajax 방식 요청 처리 페이지
	@RequestMapping(value="/registerFileForm", method=RequestMethod.GET)
	public String ajaxRegisterFileForm() {
		log.info("ajaxRegisterFileForm() 실행");
		return "member/ajaxRegisterFile";
	}
	
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file){
		log.info("ResponseEntity() 실행");
		log.info("originalFileName : " + file.getOriginalFilename());
		return new ResponseEntity<String>("UPLOAD SUCCESS", HttpStatus.OK);
	}
}

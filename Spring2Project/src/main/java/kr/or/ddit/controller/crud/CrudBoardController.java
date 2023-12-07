package kr.or.ddit.controller.crud;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/crud/board")
public class CrudBoardController {
	
	@Inject
	private IBoardService service;
	
	@PostConstruct
	public void init() {
		log.info("aopProxy 상태(interface 기반) : {}", AopUtils.isAopProxy(service));
		log.info("aopProxy 상태(class 상속 기반) : {}", AopUtils.isCglibProxy(service));
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String crudRegisterForm() {
		log.info("crudRegisterForm() 실행");
		
		return "crud/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String crudRegister(Board board, Model model) throws IOException {
		log.info("crudRegister() 실행");
		
		service.register(board);
		// 게시글을 입력 후 최신 게시글 번호가 담겨있다 (boardNo)
		log.info("게시글 등록 후 만들어진 최신 게시글 번호 : " + board.getBoardNo());
		
		model.addAttribute("msg", "등록이 완료되었습니다!");
		
		return "crud/success";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String crudList(Model model) {
		log.info("crudList() 실행");
		
		List<Board> boardList = service.list();
		model.addAttribute("boardList", boardList);
		
		return "crud/list";
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String crudRead(int boardNo, Model model) {
		log.info("crudRead() 실행");
		
		Board board = service.read(boardNo);
		model.addAttribute("board", board);
		
		return "crud/read";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String crudModifyForm(int boardNo, Model model) {
		log.info("crudModifyForm() 실행");
		
		Board board = service.read(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("status", "u");	// 수정을 진행합니다 라는 flag
		
		return "crud/register";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String crudModify(Board board, Model model) {
		log.info("crudModify() 실행");
		
		service.modify(board);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		
		return "crud/success";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String crudDelete(int boardNo, Model model) {
		log.info("crudDelete() 실행");
		
		service.remove(boardNo);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		
		return "crud/success";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String crudSearch(String title, Model model) {
		log.info("crudSearch() 실행");
		
		Board board = new Board();
		board.setTitle(title);
		
		List<Board> boardList = service.search(board);
		model.addAttribute("boardList", boardList);
		model.addAttribute("board", board);
		
		return "crud/list";
	}
}

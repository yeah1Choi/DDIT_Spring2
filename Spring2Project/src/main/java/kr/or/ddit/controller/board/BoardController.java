package kr.or.ddit.controller.board;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Board;

@Controller
@RequestMapping("/board")
public class BoardController {
	/*
	 * 	[ 3장. 컨트롤러 요청 매핑 ] 
	 * 		1. 요청 경로 매핑 
	 * 			- @RequestMapping의 value 속성에 요청 경로를 설정 
	 * 			- 요청 경로는 반드시 설정해야하는 필수 정보 
	 * 			- 속성이 하나일 때는 속성명 생략 가능 (default는 value) 
	 * 			- 컨트롤러의 클래스 레벨과 메서드 레벨로 지정할 수 있다. 
	 * 			- 클래스 레벨로 요청 경로를 지정하면 메서드 레벨에서 지정한 경로의 기본 경로로 취급 
	 * 			- 클래스 레벨의 요청 경로에 메서드 레벨의 요청 경로를 덧붙인 형태가 최종 경로가 된다.
	 */

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/register")
	public void registerForm() {
		log.info("registerForm() 실행");
	}

	@RequestMapping(value = "/modify")
	public void modifyForm() {
		log.info("modifyForm() 실행");
	}

	@RequestMapping(value = "/list")
	public void list() {
		log.info("list() 실행");
	}

	/*
	 * 		2. 경로 패턴 매핑 
	 * 			- 요청 경로를 동적으로 표현이 가능한 경로 패턴을 지정할 수 있다. 
	 * 			- URL 경로 상의 변하는 값을 경로 변수로 취급한다 
	 * 			- 경로 변수에 해당하는 값을 파라미터 변수에 설정할 수 있다.
	 */

	@RequestMapping(value = "/read/{boardNo}")
	public String read(@PathVariable("boardNo") int boardNo) {
		log.info("read() 실행");
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		return "board/read";
//		return "board/read"; 이걸 받는 애는 requestReserver
	}

	/*
	 * 		3. HTTP 메서드 매핑 
	 * 			- method 속성을 사용하여 HTTP 메서드를 매핑조건을 지정할 수 있다. 
	 * 			- 화면으로 응답하는 경우 HTTP 메서드로 GET 방식과 POST 방식 두가지를 사용할 수 있다
	 */

	// HTTP 메서드 매핑 실행 페이지
	@RequestMapping(value = "/formHome", method = RequestMethod.GET)
	public String formHome() {
		log.info("formHome() 실행");
		return "formHome";
	}

	// register 경로에 GET 방식
	@RequestMapping(value = "/http/register", method = RequestMethod.GET)
	public String registerFormHttp() {
		log.info("registerFormHttp() 실행, GET 방식으로 요청");
		return "success";
	}

	// register 경로에 POST 방식
	@RequestMapping(value = "/http/register", method = RequestMethod.POST)
	public String registerHttp() {
		log.info("registerHttp() 실행");
		return "success";
	}

	// modify 경로에 GET 방식
	@RequestMapping(value = "/http/modify", method = RequestMethod.GET)
	public String modifyFormHttp() {
		log.info("modifyFormHttp() 실행");
		return "success";
	}

	// modify 경로에 POST 방식
	@RequestMapping(value = "/http/modify", method = RequestMethod.POST)
	public String modifyHttp() {
		log.info("modifyHttp() 실행");
		return "success";
	}

	// remove 경로에 POST 방식
	@RequestMapping(value = "/http/remove", method = RequestMethod.POST)
	public String removeHttp() {
		log.info("removeHttp() 실행");
		return "success";
	}

	// list 경로에 GET 방식
	@RequestMapping(value = "/http/list", method = RequestMethod.GET)
	public String listHttp() {
		log.info("listHttp() 실행");
		return "success";
	}

	/*
	 * 		4. Params 매핑 
	 * 			- 요청 파라미터를 매핑 조건으로 지정하는경우에는 params 속성을 사용한다 
	 * 			- 버튼이나 링크에 따라 호출할 메서드를 바꿔야할 때 사용한다. 
	 * 			- name 속성 값이 params 속성에 들어감
	 * 
	 * 			** Params 매핑 테스트 페이지 
	 * 			> board/list.jsp 
	 * 			> board/modify.jsp 
	 * 			> board/read.jsp 
	 * 			> board/register.jsp 
	 * 			> board/remove.jsp
	 */

	// /board/get 경로, GET 방식, "register" 요청 파라미터에 대한 처리
	@RequestMapping(value = "/get", method = RequestMethod.GET, params = "register")
	public String registerFormParamsGet() {
		log.info("registerFormParamsGet() 실행");
		return "board/register";
	}

	// /board/post 경로, POST 방식, "register" 요청 파라미터에 대한 처리
	@RequestMapping(value = "/post", method = RequestMethod.POST, params = "register")
	public String registerParamsPost() {
		log.info("registerParamsPost() 실행");
		return "board/list";
	}

	// /board/get 경로, GET 방식, "modify" 요청 파라미터에 대한 처리
	@RequestMapping(value = "/get", method = RequestMethod.GET, params = "modify")
	public String modifyFormParamsGet() {
		log.info("modifyFormParamsGet() 실행");
		return "board/modify";
	}

	// /board/post 경로, POST 방식, "modify" 요청 파라미터에 대한 처리
	@RequestMapping(value = "/post", method = RequestMethod.POST, params = "modify")
	public String modifyParamsPost() {
		log.info("modifyParamsPost() 실행");
		return "board/list";
	}

	// /board/get 경로, GET 방식, "remove" 요청 파라미터에 대한 처리
	@RequestMapping(value = "/get", method = RequestMethod.GET, params = "remove")
	public String removeFormParamsGet() {
		log.info("removeFormParamsGet() 실행");
		return "board/remove";
	}

	// /board/post 경로, POST 방식, "remove" 요청 파라미터에 대한 처리
	@RequestMapping(value = "/post", method = RequestMethod.POST, params = "remove")
	public String removeParamsGet() {
		log.info("removeParamsGet() 실행");
		return "board/list";
	}

	// /board/list 경로, GET 방식, "list" 요청 파라미터에 대한 처리
	@RequestMapping(value = "/get", method = RequestMethod.GET, params = "list")
	public String listParamsGet() {
		log.info("listParamsGet() 실행");
		return "board/list";
	}

	// /board/read 경로, GET 방식, "read" 요청 파라미터에 대한 처리
	@RequestMapping(value = "/get", method = RequestMethod.GET, params = "read")
	public String readParamsGet() {
		log.info("readParamsGet() 실행");
		return "board/read";
	}

	/*
	 * 		5. Headers 매핑 - 요청 헤더를 매핑 조건으로 지정하는 경우에는 headers 속성을 사용한다.
	 */

	// Headers 매핑을 테스트할 페이지
	@RequestMapping(value = "/ajaxHome", method = RequestMethod.GET)
	public String ajaxHome() {
		log.info("ajaxHome() 실행");
		return "ajaxHome";
	}

	// Headers 매핑이지만, headers 설정을 하지 않고 요청
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyPut(@PathVariable("boardNo") int boardNo, @RequestBody Board board) {
		log.info("modifyPut() 실행");
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		log.info("넘겨받은 데이터들 (boardNo) : " + board.getBoardNo());
		log.info("넘겨받은 데이터들 (title) : " + board.getTitle());
		log.info("넘겨받은 데이터들 (content) : " + board.getContent());
		log.info("넘겨받은 데이터들 (writer) : " + board.getWriter());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// Headers 속성에 설정되어 있는 header 정보를 매핑으로 요청
	// Headers 속성에 'X-HTTP-Method-Override=PUT' 정보가 들어있는 요청은 이곳으로 돌아옴
	@RequestMapping(value="/{boardNo}", method=RequestMethod.PUT, headers="X-HTTP-Method-Override=PUT")
	public ResponseEntity<String> modifyByHeader(@PathVariable int boardNo, @RequestBody Board board){
		log.info("modifyByHeader() 실행");
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		log.info("넘겨받은 데이터들 (boardNo) : " + board.getBoardNo());
		log.info("넘겨받은 데이터들 (title) : " + board.getTitle());
		log.info("넘겨받은 데이터들 (content) : " + board.getContent());
		log.info("넘겨받은 데이터들 (writer) : " + board.getWriter());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	/*
	 * 		6. Content Type 매핑 
	 * 			- 요청의 Content-Type 헤더 값을 매핑 조건으로 지정하는 경우에는 consumes 속성을 사용한다.
	 */
	
	// consumes 속성값을 지정하지 않으면 기본값으로 "application/json" 미디어 타입으로 지정된다.
	@RequestMapping(value="/{boardNo}", method = RequestMethod.POST)
	public ResponseEntity<String> modifyContentType(@PathVariable("boardNo") int boardNo, @RequestBody Board board){
		log.info("modifyContentType() 실행");
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		log.info("넘겨받은 데이터들 (boardNo) : " + board.getBoardNo());
		log.info("넘겨받은 데이터들 (title) : " + board.getTitle());
		log.info("넘겨받은 데이터들 (content) : " + board.getContent());
		log.info("넘겨받은 데이터들 (writer) : " + board.getWriter());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// consumes 속성값에 "application/json" 미디어 타입으로 지정한다.
	@RequestMapping(value="/{boardNo}", method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<String> modifyByJSON(@PathVariable("boardNo") int boardNo, @RequestBody Board board){
		log.info("modifyByJSON() 실행");
		
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		log.info("넘겨받은 데이터들 (boardNo) : " + board.getBoardNo());
		log.info("넘겨받은 데이터들 (title) : " + board.getTitle());
		log.info("넘겨받은 데이터들 (content) : " + board.getContent());
		log.info("넘겨받은 데이터들 (writer) : " + board.getWriter());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// consumes 속성값에 "application/xml" 미디어 타입으로 지정한다.
	@RequestMapping(value="/{boardNo}", method = RequestMethod.PUT, consumes="application/xml")
	public ResponseEntity<String> modifyByXML(@PathVariable("boardNo") int boardNo, @RequestBody Board board){
		log.info("modifyByXML() 실행");
		
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		log.info("넘겨받은 데이터들 (boardNo) : " + board.getBoardNo());
		log.info("넘겨받은 데이터들 (title) : " + board.getTitle());
		log.info("넘겨받은 데이터들 (content) : " + board.getContent());
		log.info("넘겨받은 데이터들 (writer) : " + board.getWriter());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	/*
	 * 		7. Accept 매핑 
	 * 			- 요청 Accept 헤더 값을 매핑 조건으로 지정하는 경우에는 produces 속성을 사용한다.
	 */
	
	// produces 속성값을 지정하지 않으면 기본값인 "application/json" 미디어 타입으로 지정된다.
	@RequestMapping(value="/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<Board> readAccept(@PathVariable("boardNo") int boardNo){
		log.info("readAccept() 실행");
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setTitle("제목입니다.");
		board.setContent("내용입니다.");
		board.setWriter("a001");
		
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{boardNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Board> readToJSON(@PathVariable("boardNo") int boardNo){
		log.info("readToJSON() 실행");
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		
		String addStr = "_json";
		Board board = new Board();
		board.setTitle("제목입니다."+addStr);
		board.setContent("내용입니다."+addStr);
		board.setWriter("a001"+addStr);
		
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{boardNo}", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<Board> readToXML(@PathVariable("boardNo") int boardNo){
		log.info("readToXML() 실행");
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		
		String addStr = "_xml";
		Board board = new Board();
		board.setTitle("제목입니다."+addStr);
		board.setContent("내용입니다."+addStr);
		board.setWriter("a001"+addStr);
		
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String boardSearch(String keyword, Model model) {
		model.addAttribute("keyword", keyword);
		return "board/search";
	}
}

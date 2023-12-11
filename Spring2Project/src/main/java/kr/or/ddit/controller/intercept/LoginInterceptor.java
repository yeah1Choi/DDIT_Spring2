package kr.or.ddit.controller.intercept;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

	/*
	 * LoginInterceptor 상황 시나리오 'http://localhost/login1'을 요청해 로그인 페이지를 요청한다. 이 때,
	 * 해당 컨트롤러 메소드가 실행되기 전에 LoginInterceptor의 preHandle이 동작하고 'userInfo' 세션명을 가진
	 * 세션정보를 조회한다. 조회된 정보가 존재한다면, 세션을 삭제처리한다. 그리고 타겟을 거쳐 다시 인터셉터로 넘어올 때 데이터 전달자가
	 * 전달해준 회원정보가 존재한다면 'userInfo'키로 회원 정보를 세션에 등록하고 '/'로 리다이렉트한다. 그렇지 않은 경우는 해당 타겟을
	 * 정상 실행 후 리턴하는 결과페이지로 이동한다.
	 */

	private static final String USER_INFO = "userInfo";

	// 지정된 컨트롤러의 동작 이전에 가로채는 역할로 사용한다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle() 실행..!");

		String requestURL = request.getRequestURL().toString(); // http://localhost/login1
		String requestURI = request.getRequestURI().toString(); // /login1

		log.info("requestURL : " + requestURL);
		log.info("requestURI : " + requestURI);

		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();

		log.info("Bean : " + method.getBean()); // kr.or.ddit.controller.login.LoginInterceptor@312r2dta
		log.info("method : " + methodObj); 		// public java.lang.String kr.or.ddit.controller.login.LoginController.loginForm()

		HttpSession session = request.getSession();
		if (session.getAttribute(USER_INFO) != null) {
			session.removeAttribute(USER_INFO);
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle() 실행..!");

		String requestURL = request.getRequestURL().toString(); // http://localhost/login1
		String requestURI = request.getRequestURI().toString(); // /login1

		log.info("requestURL : " + requestURL);
		log.info("requestURI : " + requestURI);

		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();

		log.info("Bean : " + method.getBean()); // kr.or.ddit.controller.login.LoginInterceptor@312r2dta
		log.info("method : " + methodObj); 		// public java.lang.String kr.or.ddit.controller.login.LoginController.loginForm()
		
		HttpSession session = request.getSession();
		
		// 컨트롤러 메소드를 거쳤다가 postHandle로 넘어오면서 전달된 user라는 키에 value로 member가 담긴 값이
		// Model에 담겨져 있다. 그 중에 'user'로 넘긴 값이 로그인 후 인증된 회원 1명의 정보가 담긴 MemberVO 자바빈즈 객체가 되고
		// 객체가 null이 아닌 경우 메인화면으로 리다이렉트 처리한다.
		ModelMap modelMape =  modelAndView.getModelMap();
		Object member = modelMape.get("user");
		
		if(member != null) {
			log.info("member : " + member);
			log.info("member != null");
			session.setAttribute(USER_INFO, member);
			response.sendRedirect("/");
		}
	}

	// DispatcherServlet의 화면 처리가 완료된 상태에서 처리한다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String requestURL = request.getRequestURL().toString(); // http://localhost/login1
		String requestURI = request.getRequestURI().toString(); // /login1

		log.info("requestURL : " + requestURL);
		log.info("requestURI : " + requestURI);

		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();

		// kr.or.ddit.controller.login.LoginInterceptor@312r2dta
		log.info("Bean : " + method.getBean()); 
		// public java.lang.String kr.or.ddit.controller.login.LoginController.loginForm()
		log.info("method : " + methodObj); 		
	}

}
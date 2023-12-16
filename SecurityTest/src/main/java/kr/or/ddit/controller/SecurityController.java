package kr.or.ddit.controller;

public class SecurityController {
	/*
	 	[ 18장. 스프링 시큐리티 ]
	  		1. 스프링 시큐리티 소개
	  			- 어플리케이션에서 보안 기능을 구현하는데 사용되는 프레임 워크이다.
	  			- 스프링 시큐리티는 필터 기반으로 동작하기 때문에 스프링 MVC와 분리되어 동작한다.
	  			
	  			# 기본 보안 기능
	  			- 인증 (Authentication)
	  				> 어플리케이션 사용자의 정당성을 확인한다.
	  			- 인가 (Authorization)
	  				> 어플리케이션의 리소스나 처리에 대한 접근을 제어한다.
	  			
	  			# 시큐리티 제공 기능
	  				- 세션관리
	  				- 로그인처리
	  				- CSRF 토큰처리
	  				- 암호화처리
	  				- 자동로그인
	  			
	  			*** CSRF 용어 설명
	  			- 크로스 사이트 요청 위조는 웹 사이트 취약점 공격의 하나로, 사용자가 자신의 의지와는 무관하게 공격자가
	  			의도한 행위( 수정, 삭제, 등록 등 )를 특정 웹 사이트에 요청하게 하는 공격을 말한다.
	  				> CSRF 공격을 대비하기 위해서는 스프링 시큐리티 CSRF Token을 이용하여 인증을 진행
	  			
	  			# 시큐리티 인증 구조 - xml 형태로 tag를 활용해 진행 !
	  				클라이언트에서 타겟으로 들어가기 위해서 요청을 진행합니다. 이 때 타겟에 설정되어 있는 접근 권한이 '사용자'
	  				등급일 때로 설정되어 있다고 가정합시다. 타겟으로 접근하기 위한 요청을 날렸고 요청안에 사용자 등급에 해당하는 인가
	  				정보가 포함되어 있지 않으면 스프링 시큐리티는 인증을 진행할 수 있도록 인증 페이지(로그인 페이지)를 제공하여
	  				사용자에게 인증을 요청한다. 사용자는 아이디, 비밀번호를 입력 후 인증을 요청한다.
	  				클라이언트에서 서버로 요청한 HttpServletReaquest의 요청 객체를 AuthenticationFilter가 요청을
	  				인터셉터하는데 UsernamePasswordAuthenticationToken을 통해 인증을 진행할 토큰을 만들어
	  				AuthenticationManager에게 위임한다. 넘겨받은 id, pw를 이용해 인증을 진행하는데 성공 시,
	  				Authentication 객체 생성과 성공을 전달하고, 그렇지 않으면 Exception 에러를 발생시킨다.
	  				인증에 성공 후, 만들어진 Authentication 객체를 AuthenticationProvider에게 전달하고
	  				UserDetailService에서 넘겨받은 Authentication 객체 정보를 이용해 Database에 일치하는 회원정보
	  				를 조회하여 꺼낸다. 꺼낸 정보를 UserDetails로 만들고 최종 User객체에 회원정보를 등록한다.
	  				등록이 되면서 User Session 정보가 생성된다. 이후 스프링 시큐리티 내 SecurityContextHolder
	  				(시큐리티 인메모리)에 UserDetails정보를 저장한다. 그리고 최종적으로 JSESSIONID가 유효한지를 검증 후
	  				인증을 완료 후 타겟정보로 넘어가도록 돕는다.
	  				
	  		2. 스프링 시큐리티 설정
	  			# 환경 설정
	  				- 의존 라이브러리 설정(pom.xml 설정)
	  					> spring-security-web
	  					> spring-security-config
	  					> spring-security-core
	  					> spring-security-taglib
	  					
	  			# 웹 컨테이너 설정(web.xml 설정)
	  				- 스프링 시큐리티가 제공하는 서블릿 필터 클래스를 서블릿 컨테이너에 등록한다.
	  				- 스프링 시큐리티는 필터 기반이므로 시큐리티 필터 체인을 등록한다.
	  					> context-param의 param-value 추가
	  					(추가 파라미터 : /WEB-INF/spring/security-context.xml)
	  					> SpringSecurityFilterChain 추가
	  					
	  			# 스프링 시큐리티 설정
	  				- 스트링 시큐리티 컴포넌트를 빈으로 정의한다.
	  				- spring/security-context.xml 설정
	  				
	  			# 웹 화면 접근 정책
	  				- 웹 화면 접근 정책을 정한다. (테스트를 위한 각 화면당 접근 정책을 설정한다.)
	  				일반게시판       : 목록화면 : 모두가 접근 가능하다
	  					      : 등록화면 : 로그인한 회원만 접근 가능하다.
	  				공지사항게시판 : 목록화면 : 모두가 접근 가능하다.
	  						  : 등록화면 : 로그인한 회원만 접근 가능하다.
	  						  
	  			# 화면 설명
	  				- 컨트롤러
	  					> controller / SecurityBoardController
	  					> controller / SecurityNoticeController
	  				- 화면
	  					> board/list, register
	  					> notice/list, register
	  					
	  		3. 접근 제한 설정
	  			 - 시큐리티 설정을 통해서 특정 URI에 대한 접근을 제한할 수 있다.
	  			 
  			 	# 환경 설정
  			 		- 스프링 시큐리티 설정
  			 			> URI 패턴으로 접근 제한을 설정한다.
  			 			> security-context.xml 설정
  			 				<security:intercept-url pattern="/board/list" access="permitAll" />
  			 				<security:intercept-url pattern="/board/register" access="hasRole('ROLE-MEMBER')" />
  			 				notice도 마찬가지로 설정 !
  			 	# 화면 설정 
  			 		- 일반게시판 목록 화면 (모두 접근 가능 : permitAll)
  			 		- 일반게시판 등록 화면 (회원권한을 가진 사용자만 접근 가능 : hasRole('ROLE-MEMBER'))
  			 			> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지로 이동합니다.
  			 		- 공지사항게시판 목록 화면 (모두 접근 가능 : permitAll)
  			 		- 공지사항게시판 등록 화면 (관리자 권한을 가진 사용자만 접근 가능 : hasRole('ROLE-ADMIN'))
  			 			> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지로 이동합니다.
	  			 			
	  		4. 로그인 처리
	  			- 메모리상에 아이디와 비밀번호를 지정하고 로그인을 처리한다.
	  			- 스프링 시큐리티 5버전부터는 패스워드 암호화 처리기를 반드시 이용하도록 변경이 되었다.
	  			- 암호화 처리기를 사용하지 않도록 "{noop}"문자열을 비밀번호 앞에 사용한다.
	  			
  				# 환경 설정
  					- 스프링 시큐리티 설정
  						> security-context.xml 설정
  						<security:authentication-manager>
  							<security:authentication-provider>
  								<security:user name="member" password="{noop}1234" authrities="ROLE_MEMBER" />
  							</security:authentication-provider>
  			 			</security:authentication-manager>
  			 			
  			 	# 화면 설명
  			 		- 일반게시판 등록 화면
  			 			> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지가 연결되고, 
  			 			일반 회원 등급인 "ROLE_MEMBER" 권한을 가진 member 계정으로 로그인 후 해당페이지로 접근 가능
  			 		- 공지사항게시판 등록 화면
  			 			> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지가 연결되고, 
  			 			관리자 등급인 "ROLE_ADMIN" 권한을 가진 admin 계정으로 로그인 후 해당페이지로 접근 가능
	  			 			
	  		5. 접근 거부 처리
	  			- 접근 거부가 발생한 상황을 처리하는 접근 거부 처리자의 URI를 지정할 수 있다.
	  			# 환경 설정
	  				- 스프링 웹 설정(servlet-copntext.xml 설정)
	  					> <context:component-scan base-package="kr.or.ddit.security..." />
	  					** 필요에 의한 패키지 라인을 bean 등록하여 사용해야할 때 스프링 웹 설정에서 base-package를 
	  					설정할 수 있다.
	  					
	  				- 스프링 시큐리티 설정(security-context.xml 설정)
	  					> 접근 겁부 처리자의 URI를 지정
	  					> <security:access-denied-handler error-page="/acessError">
	  				
	  			# 접근 거부 처리
	  				- 접근 거부 처리 컨트롤러 작성
	  					> security/CommonController
	  					
	  			# 화면 설정
	  				- 일반게시판 등록 페이지
	  					> 접근 제한에 걸려 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고, 회원 권한을 가진 계정으로
	  					접근 시 접근 가능
	  				- 공지사항게시판 등록 페이지
	  					> 접근 제한에 걸려 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고, 회원권한을 가진 계정으로
	  					접근 시에 공지사항 게시판 등록 페이지는 권리자 권한으로만 접근 가능하므로 접근이 거부된다.
	  					이떼 access-denied-handler로 설정되어있는 URI로 이동하고 해당 페이지에서 접근이
	  					거부되었을 때 보여질 페이지의 정보가 나타난다.
	  					
	  		6. 사용자 정의 접근 거부 처리자
	  			- 접근 거부가 발생한 상황에 단순 메시지 처리 이상의 다양한 처리를 하고 싶다면 AccessDeniedHandler를 직접 구현한다.
	  			
	  			# 환경 설정
	  				- 스프링 시큐리티 설정(security-context.xml 설정)
	  					> id가 'customAccessDenied'를 가진 빈을 등록한다.
	  					> <security:access-denied-handler ref="customAccessDenied" />
	  			# 접근 거부 처리자 클래스 정의
	  				- CustomAccessDeniedHandler 클래스 정의
	  				> AccessDeniedHandler 인터페이스를 참조받아서  handle 메소드를 재정의하여 사용합니다.
	  				우리는 접근이 거부되었을 때 빈으로 등록해둔 CustomAccessHandler클래스가 발동해 해당 메소드가 
	  				실행되고 response 내장 객체를 활용하여 /accessError URL로 이동해 접근 거부시 보여질 페이지로 이동하지만
	  				이곳에서 덩 많은 로직을 처리할 수도 있다. (request, response 내장 객체를 이용하여 다양한 처리 가능)
	  			# 화면 설명
	  			 - 일반게시판 등록페이지
	  			 	> 접근 게한에 걸려 스프링 시큐리티가 제송하는 로그인 페이지가 나타나고, 회원 권한을 가진 계정으로 접근시 접근 가능
	  			 - 공지사항게시판 등록페이지
	  			 	> 접근 게한에 걸려 스프링 시큐리티가 제송하는 로그인 페이지가 나타나고, 회원 권한을 가진 계정으로 접근시 접근 가능
		  			 게시판 등록페이지는 관리자 권한만 접근 가능함으로 접근이 거부된다.
		  			 이 때 access-denied-handler로 설정되어있는 ref 속성에 부여된 클래스 메소드로 이동하고 
		  			 해당 페이지에서 접근이 거부되었을 때 페이지의 정보가 나타남
		  	7. 사용자 정의 로그인 페이지
		  		- 기본 로그인 페이지가 아닌 사용자가 직접 정의한 로그인 페이지를 사용한다.
		  		
	  			# 환경 설정
	  				- 스프링 시큐리티 설정(security-context.xml 설정)
	  					> <security:form-login login-page="/login" /> 설정
	  					사용자가 직접 만든 로그인 페이지로 이동할 '/login' URL을 가지고 있는 컨트롤러 메소드를 정의
	  						
	  			# 로그인 페이지 정의
	  				- 사용자가 정의한 로그인 컨트롤러
	  					> controller 패키지 안의 LoginController 생성
	  				- 사용자가 정의한 로그인 뷰
	  					> views/logForm.jsp
	  				** 시큐리티에서 제공하는 기본 로그인 페이지로 이동하지 않고, 사용자가 정의한 로그인 페이지의 URI를 요청하여
	  				 	해당 페이지에서 권한을 체크하도록 합니다. 인증이 완료되면 최초의 요청된 target URI 로 이동
	  				 	그렇지 않은 경우 사용자가 만들어놓은 접근 거부 페이지로 이동합니다.
	  				 	
	  		8. 로그인 성공 처리
	  			- 로그인을 성공한 후에 로그인 이력 로그를 기룍하는 등의 동작을 하고 싶은 경우가 있습니다.
	  			이런 경우에 AuthenticationSuccessHandler라는 인터페이스를 구현해서 로그인 성공 처리자로 지정할 수 있다.
	  			
	  			# 환경 설정
	  			- customLoginSuccess Bean 등록
	  			<security:form-login login-page="/login" authentication-success-handler-ref="customLoginSuccess" /> 추가
	  			
	  			# 로그인 성공 처리자 클래스 정의
		  			- 로그인 성공 처리자
			  			> SavedRequestAwareAuthenticationSuccessHandler는 AuthenticationSuccessHandler의 
			  			구현 클래스입니다. 인증 전에 접근을 시도한 URL로 리다이렉트라는 기능을 갖고 있으며 스프링 시큐리티에서 
			  			기본적으로 사용되는 구현 클래승비니다.
		  			- 로그인 성공 처리자 2
			  			> AuthenticationSuccessHandler 인터페이스를 직접 구현하여 인증 전에 접근을 시도한 URL로
			  			리다이렉트하는 기능을 구현한다.
	  	
	  			# 화면 설명
	  			- 일반게시판 등록 화면
	  				> 사용자가 정의한 로그인 페이지에서 회원 권한에 해당하는 계정으로 로그인 시, 성공했다면 성공처리자인 
	  				CustomLoginSuccess 클래스로 넘어가 넘겨받은 파라미터들 중 authentication 안에
	  				 principal로 User 정보를 받아서 username과 password를 출력합니다.
	  				 (출력 정보는 로그인 성공 시 인증된 회원 정보입니다.)
	  		
	  		9. 로그아웃 처리
	  			- 로그아웃을 위한 URI를 지정하고, 로그아웃 처리 후에 별도의 작업을 하기 위해서 사용자가 직접 구현한 처리자를 
	  			등록할 수 있다.
	  			
	  			# 환경 설정
	  				- 스프링 시큐리티 설정(securuty-context.xml 설정)
	  					> <security:logout-url="/logout" invalidate-session="true"/> 
	  					** logout 경로는 스프링에서 제송하는 /logout 경로로 설정한다.
	  						logout 처리 페이지에서도 action 경로는 /logout으로 설정한다.
	  			
  		 	10. JDBC를 이용한 인증/인가 처리
  		 		- 지정한 형식으로 테이블을 생성하면 JDBC를 이용해서 인증/인가를 처리할 수 있다.
  		 		- 생성한 테이블은 사용자를 관리하는 테이블(users)과 권한을 관리하는 테이블이다.
  		 		
  		 		# 데이터베이스 테이블 준비
  		 			- users, authorities 테이블 준비
  		 			
  		 		# 환경설정
  		 			- 의존 라이브러리 설정
  		 				> 데이터베이스 관련 라이브러리를 추가한다.
  		 				> 기존 데이터베이스 연결을 위한 라이브러리를 가져와 등록 (pom.xml)
  		 				
  		 		# 스프링 설정(root-context.xml)
  		 			- 데이터 소스 설정(기존 설정)
  		 			
  		 		# 스프링 시큐리티 설정 (security-context.xml 설정)
  		 			- customPasswordEncoder 빈 등록 진행
  		 				> <security:authentication-manage> 태그 설정
  		 				
  		 		# 비밀번호 암호화 처리기 클래스 정의
  		 			- 비밀번호 암호화 처리기
  		 			스프링 시큐리티 5부터는 기본적으로 PasswordEncoder를 지정해야하는데, 제대로 하려면 생성된 사용자 테이블(users)
  		 			에 비밀번호를 암호화하여 저장해야합니다. 테스트를 위해서 생성한 데이터는 암호화를 처리하지 않으므로 로그인 화면
  		 			당연히 로그인 에러가 발생할 것입니다. (암호화된 비밀번호가 날라가는게 아니라서)
  		 			그래서 암호화를 하지 않는 PasswordNoOpPasswordEncoder를 직접 구현하여 지정하면 로그인 시 암호화를 고려하지
  		 			않으므로 로그인이 정상적으로 이루어지는 것을 확인할 수 있다.
  		 			** PasswordEncoder를 참조받아서 우리가 원하는 로직으로 변경해서 사용(암호화를 사용하지 않는 루트로)
  		 				
  		 	11. 사용자 테이블 이용한 인증/인가 처리
  		 		- 스프링 시큐리티가 기본적으로 이용하는 테이블 구조를 그대로 생성해서 사용해도 되지만 기본에 구축된 회원 테이블이 있다면
  		 		약간의 작업으로 기존 테이블을 활용할 수 있다.
  		 		
  		 		# 데이터베이스 테이블 준비
  		 			- member, member_auth 테이블 준비
  		 		
  		 		# 환경 설정
  		 			- 스프링 시큐리티 설정(security-context.xml 설정)
  		 				> bcryptPasswordEncoder 빈 등록
  		 				> <security:jdbc-user-service> 태그 설정
  		 				> <security:password-encoder> 태그 설정
  		 		
  		 		# 쿼리 정의
  		 			- 인증할 때 필요한 쿼리
  		 				> select user_id, user_pw, enabled from member where user_id = ?
  		 			- 권한을 확인할 때 필요한 쿼리
  		 				> select m.user_id, m.auth from member m, member_auth ma
  		 					where ma.user_no = m.user_no and m.user_id = ?
  		 		
  		 		# BCryptPasswordEncoder 클래스를 이용하여 직접 encode된 비밀번호를 찾아 데이터베이스에 셋팅한다.
  		 		- 12번 UserDetails 정보를 등록하면서 같이 진행
  		 			- BCryptPasswordEncoder 클래스를 활용한 단방향 비밀번호 암호화
  		 				> encode() 메소드를 통해서 SHA-2 방식의 8바이트  Hash 암호를 매번 랜덤하게 생성합니다.
  		 				> 똑같은 비밀번호를 입력하더라도 암호화되는 문자열은 매번 다른 문자열을 반환한다.
  		 				
  		 				비밀번호를 입력하면 암호화된 비밀번호로 인코딩하는데, 암호화된 비밀번호와 DB 테이블에 있는 암호화된 비밀번호가 
  		 				일치한지 파악 후, 일치하면 로그인 성공으로 다음 스텝을 진행한다.
  		 				
  		 				> BCryptPasswordEncoder 클래스의 encode() 메소드를 통해 만들어지는 암호화된 해쉬 다이제스트들은 입력한
  		 				비밀번호 문자에 해당하는 수십억개의 다이제스트들 중에서 일치하는 다이제스트가 존재할 경우 비밀번호의 일치로 보고
  		 				인증을 성공시켜준다.
  		 				
  		 	12. UserDetailService 재정의
  		 		- 스프링 시큐리티의 UserDetailService를 구현하여 사용자 상세 정보를 얻어오는 메소드를 재정의한다.
  		 		
  		 		# 환경 설정
  		 			- 의존 라이브러리 설정(pom.xml 설정)
  		 				> 데이터베이스 관련 라이브러리
  		 			- 스프링 시큐리티 설정(security-context.xml 설정)
  		 				> customUserDetailService 빈 등록
  		 				> security:authentication-provider 태그 설정
  		 				
  		 		# 클래스 재정의
  		 			- UserDetailService 재정의
  		 				> security/CustomUserDetailService 클래스 생성
  		 				> 기존 사용 중인 read를 기반으로 한 readById 재정의
  		 				> CustomUserDetailService 클래스 내 loadUserByUsername 메소드에서  인코딩된 비밀번호 확인 후 데이터베이스 비밀번호 수정
  		 					> Member 테이블 내 특정 계정들 비밀번호를 암호화된 비밀번호로 수정한다.
  		 	
  		 	13. 스프링 시큐리티 표현식
  		 		- 스프링 시큐리티 표현식을 이용하면 인증 및 권한 정보에 따라 화면을 동적으로 구성할 수 있고,
  		 		로그인한 사용자 정보를 보여줄 수도 있다.
  		 		
  		 		# 공동 표현식
  		 			- hasRole([role])
  		 				> 해당 롤이 있으면 true
  		 			- hasAnyRole([role1, role2])
						>  여러 롤들 중에서 하나라도 해당하는 롤이 있으면  true
					- principal
						> 인증된 사용자의 사용자 정보(UserDetails 인터페이스를 구현한 클래스의 객체)를 의미
					- authentication
						> 인증된 사용자의 사용자 정보(Authentication 인터페이스를 구현한 클래스의 객체)를 의미
					- permitAll
						> 모든 사용자에게 허용
					- denyAll
						> 모든 사용자에게 거부
					- isAnonymous()
						> 익명의 사용자의 경우(로그인을 하지 않은 경우에 해당)
					- isAuthenticatied()
						> 인증된 사용자면 true
					- isFullyAuthenticated()
						> Remember-me로 인증된 것이 아닌 일반적인 방법으로 인증된 사용자인 경우 true
					
				# 표현식 사용
					- 표현식을 이용하여 동적 화면 구성
						> home.jsp 수정
							: 표현식을 이용한 내용 추가
					- 로그인한 사용자 정보 보여주기
						> views/board/register.jsp 수정
						> views/notice/register.jsp 수정
						
			14. 자동 로그인
				- 로그인하면 특정 시간동안 다시 로그인할 필요가 없는 기능이다.
				- 스프링 시큐리티는 메모리나 데이터베이스를 사용하여 처리한다.
				- 기능을 구현하기 위해 <security:rememer-me> 태그를 이용하려 시큐리티 설정 파일을 수정한다.
				
				# 데이터베이스 테이블
					- persistent_logins 테이블 준비
				# 환경설정
					- 스프링 시큐리티 설정(security-context.xml)
						<security:remember-me data-source-ref='dataSource' token-validity='604800'/> 태그 설정
						<security:logout logout-url='/logout' invalidate-session='true' 
							delete-cookies='remember-me, JSESSION_ID' /> 태그 설정
				# 자동 로그인
					- 로그인 상태 유지 체크박스 추가
						> logForm.jsp 수정(자동 로그인 체크 박스 만들기)
				# 자동 로그인 시 만들어지는 쿠키 정보들
					- JSESSIONID와 remember-me 쿠키가 만들어진다.
					- JSESSIONID를 삭제 후, 다시 로그인을 진행하더라도 로그인 후 진행될 페이지가 정상적으로 나타나는 것을 확인할 수 있음
						> 자동 로그인이 remember-me 쿠키에 의해서 데이터베이스에 저장되는 각hash token 값에 의해 재설정됨을 확인
						
			15. 스프링 시큐리티 어노테이션
				- 스프링 시큐리티는 어노테이션을 사용하여 필요한 설정을 추가할 수 있다.
				# 사용 어노테이션
					- @Secured
					- @PreAuthorize
					- @PostAuthorize
  		 			
	 */
}

package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	// 입력값 검증 규칙을 지정
	
	// 문자열이 null이 아니고 trim한 길이가 0보다 크다는 것을 검사
	@NotBlank(message = "아이디를 입력해주세요! X_X")
	private String userId = "a001";
	
	// @Size(max=3) : 3글자 이하여야한다
	@NotBlank(message = "이름을 입력해주세요! X_X")
	@Size(max=3)
	private String userName = "Choiyw";
	
	@NotBlank(message = "비밀번호를 입력해주세요! X_X")
	private String password = "1234";
	private int coin = 100;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	// 과거 날짜인지 검사
	
	@Email
	private String email;
	// 이메일 주소 형식 검사
	
	private String gender;
	private String hobby;
	private String[] hobbyArray;
	private List<String> hobbyList;
	private boolean foreinger;
	private String developer;
	private String nationality;

	@Valid
	private Address address;
	// 중첩된 자바빈즈의 입력값 검증을 지정
	
	@Valid
	private List<Card> cardList;
	// 자바빈즈 컬렉션의 입력값 검증을 지정
	
	private String cars;
	private String[] carArray;
	private List<String> carList;
	private String introduction;
	private String birthDay;
}
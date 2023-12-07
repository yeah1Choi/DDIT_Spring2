package kr.or.ddit.vo;

import java.util.Date;

import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Card {
	@NotBlank
	private String no;
	
	@Future
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date validMonth;
	// @Future 미래 날짜인지 검사
}

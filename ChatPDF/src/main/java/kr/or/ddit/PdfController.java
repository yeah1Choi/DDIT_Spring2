package kr.or.ddit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PdfController {

	@RequestMapping(value = "/pdf.do", method = RequestMethod.GET)
	public String pdf() {
		return "pdf";
	}
	
}

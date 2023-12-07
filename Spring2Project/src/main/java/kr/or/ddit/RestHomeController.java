package kr.or.ddit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestHomeController {
	@RequestMapping(value="/goRestHome0301", method=RequestMethod.GET)
	public Member goRestHome0301() {
		log.info("goRestHome0301() 실행");
		Member member = new Member();
		return member;
	}
	
	@RequestMapping(value="/goRestHome0401", method=RequestMethod.GET)
	public List<Member> goRestHome0401(){
		log.info("goRestHome0401() 실행");
		List<Member> list = new ArrayList<Member>();
		Member member = new Member();
		Member member2 = new Member();
		
		list.add(member);
		list.add(member2);
		
		return list;
	}
	
	@RequestMapping(value="/goRestHome0501", method=RequestMethod.GET)
	public Map<String, Member> goRestHome0501(){
		log.info("goRestHome0501() 실행");
		Map<String, Member> map = new HashMap<String, Member>();
		Member member = new Member();
		Member member2 = new Member();
		

		map.put("key1", member);
		map.put("key2", member2);
		
		return map;
	}
	
	@RequestMapping(value="/goRestHome0601", method=RequestMethod.GET)
	public ResponseEntity<Void> goHomeRest0601(){
		log.info("goHomeRest0601() 실행");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome0701", method=RequestMethod.GET)
	public ResponseEntity<String> goRestHome0701(){
		log.info("goRestHome0701() 실행");
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome0801", method=RequestMethod.GET)
	public ResponseEntity<Member> goRestHome0801(){
		log.info("goRestHome0801() 실행");
		Member member = new Member();
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome0901", method=RequestMethod.GET)
	public ResponseEntity<List<Member>> goRestHome0901(){
		log.info("goRestHome0901() 실행");
		List<Member> list = new ArrayList<Member>();
		Member member1 = new Member();
		Member member2 = new Member();
		
		list.add(member1);
		list.add(member2);
		
		return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome1001", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Member>> goRestHome1001(){
		log.info("goRestHome1001() 실행");
		Map<String, Member> map = new HashMap<String, Member>();
		Member member1 = new Member();
		Member member2 = new Member();

		map.put("key1", member1);
		map.put("key2", member2);
		
		return new ResponseEntity<Map<String, Member>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome1101", method=RequestMethod.GET)
	public ResponseEntity<byte[]> goRestHome1101(){
		log.info("goRestHome1101() 실행");
		ResponseEntity<byte[]> entity = null;
		
		InputStream in = null;
		HttpHeaders headers = new HttpHeaders();
		
		try {
			in = new FileInputStream("C:\\Users\\PC-24\\Downloads\\chichi.jfif");
			headers.setContentType(MediaType.IMAGE_JPEG);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return entity;
	}
	
	@RequestMapping(value="/goRestHome1102", method=RequestMethod.GET)
	public ResponseEntity<byte[]> goRestHome1102() throws IOException{
		log.info("goRestHome1102() 실행");
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String fileName = "DDIT_Spring2_goHome1102.jpg";
		HttpHeaders header = new HttpHeaders();
		
		try {
			in = new FileInputStream("C:\\Users\\PC-24\\Downloads\\chichi.jfif");
			header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			header.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), header, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
}

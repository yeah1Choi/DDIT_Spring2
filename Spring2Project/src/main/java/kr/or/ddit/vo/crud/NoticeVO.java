package kr.or.ddit.vo.crud;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeVO {
	private int boNo;
	private String boTitle;
	private String boContent;
	private String boWriter;
	private String boDate;
	private int boHit;
	
	private Integer[] delNoticeNo;
	private MultipartFile[] boFile;
	private List<NoticeFileVO> noticeFileList;
	
	public void setBoFile(MultipartFile[] boFile) {
		this.boFile = boFile;
		if(boFile != null) {
			List<NoticeFileVO> noticeFileList = new ArrayList<NoticeFileVO>();
			for(MultipartFile item : boFile) {
				if(StringUtils.isBlank(item.getOriginalFilename())) {
					continue;
				}
				
				NoticeFileVO noticeFileVO = new NoticeFileVO(item);
				noticeFileList.add(noticeFileVO);
			} 
			this.noticeFileList = noticeFileList;
		}
	}
}

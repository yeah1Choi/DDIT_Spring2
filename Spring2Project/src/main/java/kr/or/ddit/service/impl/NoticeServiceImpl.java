package kr.or.ddit.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.crud.notice.TelegramSendController;
import kr.or.ddit.mapper.INoticeMapper;
import kr.or.ddit.mapper.IProfileMapper;
import kr.or.ddit.mapper.loginMapper;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.crud.NoticeFileVO;
import kr.or.ddit.vo.crud.NoticeMemberVO;
import kr.or.ddit.vo.crud.NoticeVO;
import kr.or.ddit.vo.crud.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService {

	@Inject
	private INoticeMapper noticeMapper;

	@Inject
	private loginMapper loginMapper;
	
	@Inject
	private IProfileMapper profileMapper;
	
	private TelegramSendController tst = new TelegramSendController();

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeMapper.selectNoticeCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeMapper.selectNoticeList(pagingVO);
	}

	@Override
	public ServiceResult insertNotice(HttpServletRequest req, NoticeVO noticeVO) {
		ServiceResult result = null;

		int status = noticeMapper.insertNotice(noticeVO);

		if (status > 0) { // 게시글 등록 성공
			List<NoticeFileVO> noticeFileList = noticeVO.getNoticeFileList();
			try {
				// 공지사항 파일 업로드 처리
				noticeFileUpload(noticeFileList, noticeVO.getBoNo(), req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Telegram Bot API를 이용한 실시간 메시지 처리
			try {
				tst.sendGet("최원팔", noticeVO.getBoTitle());
			} catch (Exception e) {
				e.printStackTrace();
			}

			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeMapper.incrementHit(boNo); // 게시글 조회수 증가
		return noticeMapper.selectNotice(boNo); // 게시글 번호에 해당하는 게시글 정보 가져오기
	}

	@Override
	public ServiceResult updateNotice(HttpServletRequest req, NoticeVO noticeVO) {
		ServiceResult result = null;
		int status = noticeMapper.updateNotice(noticeVO); // 게시글 수정
		if (status > 0) { // 게시글 수정 완료

			// 게시글 정보에서 파일 목록 가져오기
			List<NoticeFileVO> noticeFileList = noticeVO.getNoticeFileList();
			try {
				// 공지사항 파일 업로드
				noticeFileUpload(noticeFileList, noticeVO.getBoNo(), req);

				// 기존에 등록되어있는 파일 목록 중, 수정하기 위해서 X 버튼을 눌러 삭제 처리로 넘겨준 팡리 번호들
				Integer[] delNoticeNo = noticeVO.getDelNoticeNo();
				if (delNoticeNo != null) {
					for (int i = 0; i < delNoticeNo.length; i++) {
						// 삭제할 파일 번호 목록들 중, 파일 번호에 해당하는 공지사항 파일정보를 가져온다.
						NoticeFileVO noticeFileVO = noticeMapper.selectNoticeFile(delNoticeNo[i]);
						noticeMapper.deleteNoticeFile(delNoticeNo[i]); // 파일번호에 해당하는 파일 데이터를 삭제
						File file = new File(noticeFileVO.getFileSavepath());
						file.delete(); // 기존 파일이 업로드되어 있던 경로에 파일 삭제
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteNotice(HttpServletRequest req, int boNo) {
		ServiceResult result = null;

		// 공지사항 파일 데이터를 삭제하기 위한 준비 (파일 적용시)
		NoticeVO noticeVO = noticeMapper.selectNotice(boNo); // 게시글 번호에 해당하는 공지사항 게시글 정보 가져오기
		noticeMapper.deleteNoticeFileByBoNo(boNo); // 게시글 번호에 해당하는 파일 데이터 삭제

		int status = noticeMapper.deleteNotice(boNo); // 일반적인 게시글 삭제
		if (status > 0) {
			List<NoticeFileVO> noticeFileList = noticeVO.getNoticeFileList();
			if (noticeFileList != null && noticeFileList.size() > 0) {
				// D:\99_Ckass...\workspace\.metadata\.plugins...\Project명\resources\notice\boNo
				// asd6587asdf68asdf.jpg
				// '/'기준으로 잘라준다.
				String[] filePath = noticeFileList.get(0).getFileSavepath().split("/");

				String path = filePath[0];
				deleteFolder(req, path);
			}

			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	private void deleteFolder(HttpServletRequest req, String path) {
		// UUID + 원본파일명 전 폴더 경로를 folder 파일객체로 잡는다.
		File folder = new File(path);

		if (folder.exists()) { // 경로가 존재한다면
			File[] folderList = folder.listFiles(); // 폴더 안에 있는 파일들의 목록을 가져온다.

			for (int i = 0; i < folderList.length; i++) {
				if (folderList[i].isFile()) { // 폴더 안의 있는 파일이 파일일때
					// 폴더 안에 파일을 차례대로 삭제
					folderList[i].delete();
				} else {
					// 폴더 안에 있는 파일이 폴더일 때 재귀함수 호출(폴더 안으로 들어가서 재처리할 수 있도록)
					deleteFolder(req, folderList[i].getPath());
				}
			}
			folder.delete(); // 폴더 삭제
		}
	}

	@Override
	public NoticeMemberVO loginCheck(NoticeMemberVO member) {
		return loginMapper.loginCheck(member);
	}

	@Override
	public ServiceResult idCheck(String memId) {
		ServiceResult result = null;
		NoticeMemberVO member = loginMapper.idCheck(memId);

		if (member != null) {
			result = ServiceResult.EXIST;
		} else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}
	
	@Override
	public NoticeMemberVO findId(NoticeMemberVO memberVO) {
		return loginMapper.findId(memberVO);
	}
	
	@Override
	public NoticeMemberVO findPw(NoticeMemberVO memberVO) {
		return loginMapper.findPw(memberVO);
	}

	@Override
	public ServiceResult signup(HttpServletRequest req, NoticeMemberVO memberVO) {
		ServiceResult result = null;

		// 회원가입 시, 프로필 이미지로 파일을 업로드 하는데 이때 업로드 할 서버 경로
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		File file = new File(uploadPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String profileImg = ""; // 회원정보에 추가될 프로필 이미지 경로
		try {
			// 넘겨받은 회우너정보에서 파일 데이터 가져오기
			MultipartFile profileImgFile = memberVO.getImgFile();

			// 넘겨받은 파일 데이터가 존재할 때
			if (profileImgFile.getOriginalFilename() != null && !profileImgFile.getOriginalFilename().equals("")) {
				String fileName = UUID.randomUUID().toString(); // UUID 파일명 생성
				fileName += "_" + profileImgFile.getOriginalFilename(); // UUID_원본파일명으로 파일명 생성
				uploadPath += "/" + fileName; // /resources/profile/uuid_원본파일명

				profileImgFile.transferTo(new File(uploadPath)); // 해당 위치에 파일복사
				profileImg = "/resources/profile/" + fileName; // 파일 복사가 일어난 파일의 위치로 접근하기 위한 URI 설정
			}

			memberVO.setMemProfileImg(profileImg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int status = loginMapper.signup(memberVO);
		if (status > 0) { // 등록 성공
			result = ServiceResult.OK;
		} else { // 등록 실패
			result = ServiceResult.FAILED;
		}

		return result;
	}

	private void noticeFileUpload(List<NoticeFileVO> noticeFileList, int boNo, HttpServletRequest req)
			throws IOException {
		String savePath = "/resources/notice/";

		if (noticeFileList != null) { // 넘겨받은 파일 데이터가 존재
			if (noticeFileList.size() > 0) {
				for (NoticeFileVO noticeFileVO : noticeFileList) {
					String saveName = UUID.randomUUID().toString(); // UUID의 랜덤 파일명 생성

					// 파일명 설정할 때, 원본 파일명의 공백을 '_'로 변경
					saveName = saveName + "_" + noticeFileVO.getFileName().replaceAll(" ", "_");
					// 디버깅 및 확장자 추출 참고
					String endFileName = noticeFileVO.getFileName().split("\\.")[1];

					String saveLocate = req.getServletContext().getRealPath(savePath + boNo);
					File file = new File(saveLocate);
					if (!file.exists()) {
						file.mkdirs();
					}
					saveLocate += "/" + saveName;

					noticeFileVO.setBoNo(boNo); // 게시글 번호 설정
					noticeFileVO.setFileSavepath(saveLocate); // 파일 업로드 경로 설정
					noticeMapper.insertNoticeFile(noticeFileVO); // 게시글 파일 데이터 추가

					File saveFile = new File(saveLocate);

					// [방법1]
//					InputStream is = noticeFileVO.getItem().getInputStream();
//					FileUtils.copyInputStreamToFile(is, saveFile);

					// [방법2]
					noticeFileVO.getItem().transferTo(saveFile); // 파일 복사
				}
			}
		}
	}

	@Override
	public NoticeFileVO noticeDownload(int fileNo) {
		NoticeFileVO noticeFileVO = noticeMapper.noticeDownload(fileNo);
		if (noticeFileVO == null) {
			throw new RuntimeException();
		}
		noticeMapper.incrementNoticeDowncount(fileNo); // 다운로드 횟수 증가
		return noticeFileVO;
	}

	@Override
	public NoticeMemberVO selectMember(String memId) {
		return profileMapper.selectMember(memId);
	}

	@Override
	public ServiceResult profileUpdate(HttpServletRequest req, NoticeMemberVO memberVO) {
		ServiceResult result = null;
		
		// 프로필 이미지를 업로드하기 위한 서버 경로 (/resources/profile)
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String profileImg = "";
		try {
			MultipartFile profileImgFile = memberVO.getImgFile();
			if(profileImgFile.getOriginalFilename() != null && profileImgFile.getOriginalFilename().equals("")) {
				String fileName = UUID.randomUUID().toString();
				fileName += "_" + profileImgFile.getOriginalFilename();
				uploadPath += "/" + fileName;
				profileImgFile.transferTo(new File(uploadPath));
				profileImg = "/resources/profile/" + fileName;
			}
			memberVO.setMemProfileImg(profileImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int status = profileMapper.profileUpdate(memberVO);
		if(status > 0) {	// 수정 성공
			result = ServiceResult.OK;
		} else {			// 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}


}

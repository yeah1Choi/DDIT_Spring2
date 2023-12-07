package kr.or.ddit.controller.crud.notice;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

// TelegramSendController 클래스를 인스턴스로 사용하기 때문에 어노테이션을 따로 붙히지 않는다.
public class TelegramSendController {

	// 텔레그램 BOT이 초대되어 있는 방으로 메시지 전송된다.
	public void sendGet(String name, String title) throws Exception {
		String chat_id = "-951855941";		// 채팅방 ID
		String urlName = "https://api.telegram.org/bot6310783618:AAEI3ptPtCArMaRYoKBHxfHhjOuYcDbVUaY/sendMessage";		// 메시지 전송 타겟 URL
		String text = "";			// 전송 메시지
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String tiem1 = format.format(time);
		
		text = name + "님께서 글 작성을 완료했습니다 !\n" 
					+ "[제목]" + title + "\n"
					+ "[작성일]" + tiem1 + "\n";
		
		URL url = new URL(urlName + "?chat_id=" + chat_id + "&text=" + URLEncoder.encode(text, "UTF-8"));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		int respCode = conn.getResponseCode();
		System.out.println("텔레그램 봇 API를 활용한 메시지 처리 결과 : " + respCode);
	}
}

package com.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.domain.Notice;
import com.web.persistence.NoticeRepository;

@SpringBootTest
public class NoticeTest {
	
	
	@Autowired
	private NoticeRepository noticeRepo;
	
	@Test
	public void testNoticeInsert() {
		
		
		// 관리자A. 공지글
		for(int i=1; i<20; i++) {
			Notice notice = new Notice();
			notice.setNoticeSeq(0L);  // 공지글 번호
			notice.setNoticeWriter("adminA"); // 작성자
			notice.setNoticeTitle("공지" + i); // 공지글 제목
			notice.setNoticeContents("내용은 어쩌구저쩌구" + (i+2)); // 공지글 내용
			notice.setNoticeImageName("img" + i); // 공지글 이미지
			notice.setNoticeChoice(1L); //  // 공지 선택 ( 1:공지, 2:점검 ) 
			noticeRepo.save(notice); 
		}
		
		// 관리자A. 점검글
		for(int i=1; i<20; i++) {
			Notice notice = new Notice();
			notice.setNoticeSeq(0L);  // 공지글 번호
			notice.setNoticeWriter("adminA"); // 작성자
			notice.setNoticeTitle("점검" + i); // 공지글 제목
			notice.setNoticeContents("내용은 어쩌구저쩌구" + (i+2)); // 공지글 내용
			notice.setNoticeImageName("img" + i); // 공지글 이미지
			notice.setNoticeChoice(2L); //  // 공지 선택 ( 1:공지, 2:점검 ) 
			noticeRepo.save(notice); 
		}
		
		// 관리자B. 공지글
		for(int i=1; i<20; i++) {
			Notice notice = new Notice();
			notice.setNoticeSeq(0L);  // 공지글 번호
			notice.setNoticeWriter("adminB"); // 작성자
			notice.setNoticeTitle("공지" + i); // 공지글 제목
			notice.setNoticeContents("내용은 어쩌구저쩌구" + (i+2)); // 공지글 내용
			notice.setNoticeImageName("img" + i); // 공지글 이미지
			notice.setNoticeChoice(1L); //  // 공지 선택 ( 1:공지, 2:점검 ) 
			noticeRepo.save(notice); 
		}
		
		// 관리자B. 점검글
		for(int i=1; i<20; i++) {
			Notice notice = new Notice();
			notice.setNoticeSeq(0L);  // 공지글 번호
			notice.setNoticeWriter("adminB"); // 작성자
			notice.setNoticeTitle("점검" + i); // 공지글 제목
			notice.setNoticeContents("내용은 어쩌구저쩌구" + (i+2)); // 공지글 내용
			notice.setNoticeImageName("img" + i); // 공지글 이미지
			notice.setNoticeChoice(2L); //  // 공지 선택 ( 1:공지, 2:점검 ) 
			noticeRepo.save(notice); 
		}
		
	}

}

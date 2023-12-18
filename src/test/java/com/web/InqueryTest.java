package com.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.domain.Board;
import com.web.domain.Inquery;
import com.web.domain.Member;
import com.web.domain.Report;
import com.web.persistence.BoardRepository;
import com.web.persistence.InqueryRepository;
import com.web.persistence.MemberRepository;
import com.web.persistence.ReportRepository;
import com.web.service.SupportService;

@SpringBootTest
public class InqueryTest {
	@Autowired
	private InqueryRepository inqueryRepository;
	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BoardRepository boardRepository;
	
//	@Test
//	public void inqAndResIns100() {
//		for(int i=1;i<50;i++) {
//			Inquery inquery = new Inquery();
//			inquery.setInqueryWriter(i+" 번 작성자");
//			inquery.setInqueryTitle(i+ " 번 제목");
//			inquery.setInqueryContent(i + " 번 내용");
//			inquery.setInquerySelect("1");
//			inqueryRepository.save(inquery);
//		}
//		for(int i=50;i<100;i++) {
//			Inquery inquery = new Inquery();
//			inquery.setInqueryWriter(i+" 번 작성자");
//			inquery.setInqueryTitle(i+ " 번 제목");
//			inquery.setInqueryContent(i + " 번 내용");
//			inquery.setInquerySelect("2");
//			inqueryRepository.save(inquery);
//		}
//		for(int i=1;i<50;i++) {
//			Report report = new Report();
//			report.setReportWriter(i+" 번 작성자");
//			report.setReportTitle(i+ " 번 제목");
//			report.setReportContent(i + " 번 내용");
//			report.setReportSelect("1");
//			reportRepository.save(report);
//		}
//		for(int i=50;i<100;i++) {
//			Report report = new Report();
//			report.setReportWriter(i+" 번 작성자");
//			report.setReportTitle(i+ " 번 제목");
//			report.setReportContent(i + " 번 내용");
//			report.setReportSelect("2");
//			reportRepository.save(report);
//		}
//	}
//	@Test
//	public void memberInput() {
//		for(int i=1;i<50;i++) {
//			Member member = new Member();
//			member.setId(i+"");
//			member.setNickName("별명"+i);
//			member.setTotalPoint(i*10L);
//			memberRepository.save(member);
//		}
//	}
	@Test
	public void board() {
		for(int i=0; i<100; i++) {
			Board board = new Board();
			board.setBoardChoice(1L);
			board.setBoardComment(i+"+asd");
			board.setBoardContents("asdasd");
			board.setBoardTitle(i+"asdqas");
			board.setBoardUserId(i+"writer");
			boardRepository.save(board);
		}
	}
}

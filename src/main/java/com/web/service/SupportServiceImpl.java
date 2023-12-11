package com.web.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.Inquery;
import com.web.domain.Member;
import com.web.domain.Report;
import com.web.persistence.InqueryRepository;
import com.web.persistence.ReportRepository;

@Service
public class SupportServiceImpl implements SupportService {
	
	@Autowired
	private InqueryRepository inqueryRepo;
	@Autowired
	private ReportRepository reportRepo;
	
	@Override
	public void insertInquery(MultipartHttpServletRequest mul) {
		Inquery inquery = new Inquery();
		inquery.setInqueryWriter(mul.getParameter("inqueryWriter"));
		inquery.setInqueryTitle(mul.getParameter("inqueryTitle"));
		inquery.setInqueryContent(mul.getParameter("inqueryContent"));
		inquery.setInquerySelect(mul.getParameter("inquerySelect"));
		
		MultipartFile file = mul.getFile("file");
		
		if(file.getSize() !=0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar calendar = Calendar.getInstance();
			String sysFileName = sdf.format(calendar.getTime());
			sysFileName += file.getOriginalFilename();
			File saveFile = new File(INQUERY_IMAGE_REPO + "/" + sysFileName); 
			inquery.setFile(sysFileName);
			try {
				file.transferTo(saveFile);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			inquery.setFile("nan");
		}
		inqueryRepo.save(inquery);
	}

	@Override
	public void getInqueryList(Model model, Inquery inquery, Pageable pageable, Member member) {
		// TODO Auto-generated method stub
		String userId = member.getId();
		Page<Inquery> inqueryList = inqueryRepo.findByInqueryWriter(pageable, userId);
		int nowPage = inqueryList.getPageable().getPageNumber()+1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, inqueryList.getTotalPages());
		
		model.addAttribute("inqueryList", inqueryList);
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
	}
	//-----------------------------------------------------------------
	@Override
	public void insertReport(MultipartHttpServletRequest mul) {
		Report report = new Report();
		report.setReportWriter(mul.getParameter("reportWriter"));
		report.setReportTitle(mul.getParameter("reportTitle"));
		report.setReportContent(mul.getParameter("reportContent"));
		report.setReportSelect(mul.getParameter("reportSelect"));
		MultipartFile file = mul.getFile("file");
		if(file.getSize() !=0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar calendar = Calendar.getInstance();
			String sysFileName = sdf.format(calendar.getTime());
			sysFileName += file.getOriginalFilename();
			File saveFile = new File(REPORT_IMAGE_REPO + "/" + sysFileName); 
			report.setFile(sysFileName);
			try {
				file.transferTo(saveFile);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			report.setFile("nan");
		}
		reportRepo.save(report);
	}
	
	@Override
	public void getReportList(Model model, Report report, Pageable pageable, Member member) {
		// TODO Auto-generated method stub
		String userId = member.getId();
		Page<Report> reportList = reportRepo.findByReportWriter(pageable, userId); 
		int nowPage = reportList.getPageable().getPageNumber()+1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, reportList.getTotalPages());
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
	}

	@Override
	public void find3List(Inquery inquery, Report report, Pageable pageable1, Pageable pageable2, Pageable pageable3,
						  String searchKeyword1, String searchKeyword2, String searchKeyword3, Model model) {
		// TODO Auto-generated method stub
		Page<Inquery> inqueryList1;
		if(searchKeyword1!=null) {
			inqueryList1 = inqueryRepo.findByInquerySelectAndInqueryTitleContainingAndInqueryAnswerIsNull(pageable1, "1", searchKeyword1);
		}else {
			inqueryList1 = inqueryRepo.findByInquerySelectAndInqueryAnswerIsNull(pageable1, "1");
		}
		int nowPage1 = inqueryList1.getPageable().getPageNumber()+1;
		int startPage1 = Math.max(nowPage1 - 4, 1);
		int endPage1 = Math.min(nowPage1 + 5, inqueryList1.getTotalPages());
		
		model.addAttribute("inqueryList1", inqueryList1);
		model.addAttribute("nowPage1",nowPage1);
		model.addAttribute("startPage1",startPage1);
		model.addAttribute("endPage1",endPage1);
		
		Page<Inquery> inqueryList2;
		if(searchKeyword2!=null) {
			inqueryList2 = inqueryRepo.findByInquerySelectAndInqueryTitleContainingAndInqueryAnswerIsNull(pageable2, "2", searchKeyword2);
		}else {
			inqueryList2 = inqueryRepo.findByInquerySelectAndInqueryAnswerIsNull(pageable2, "2");
		}
		int nowPage2 = inqueryList2.getPageable().getPageNumber()+1;
		int startPage2 = Math.max(nowPage2 - 4, 1);
		int endPage2 = Math.min(nowPage2 + 5, inqueryList2.getTotalPages());
		
		model.addAttribute("inqueryList2", inqueryList2);
		model.addAttribute("nowPage2",nowPage2);
		model.addAttribute("startPage2",startPage2);
		model.addAttribute("endPage2",endPage2);
		
		Page<Report> reportList;
		if(searchKeyword3!=null) {
			reportList = reportRepo.findByReportTitleContainingAndReportAnswerIsNull(pageable3, searchKeyword3);
		}else {
			reportList = reportRepo.findByReportAnswerIsNull(pageable3);
		}
		int nowPage3 = reportList.getPageable().getPageNumber()+1;
		int startPage3 = Math.max(nowPage3 - 4, 1);
		int endPage3 = Math.min(nowPage3 + 5, reportList.getTotalPages());
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("nowPage3",nowPage3);
		model.addAttribute("startPage3",startPage3);
		model.addAttribute("endPage3",endPage3);
	}

	@Override
	public void getInqueryById(Long inquerySeq, Model model) {
		// TODO Auto-generated method stub
		Optional<Inquery> optional = inqueryRepo.findById(inquerySeq);
		Inquery inquery = optional.get();
		model.addAttribute("inquery", inquery);
	}
	
	
	@Override
	public void getReportById(Long reportSeq, Model model) {
		// TODO Auto-generated method stub
		Optional<Report> optional = reportRepo.findById(reportSeq);
		Report report = optional.get();
		model.addAttribute("report", report);
	}

	@Override
	public void answerInq(Long inquerySeq) {
		// TODO Auto-generated method stub
		Optional<Inquery> optional = inqueryRepo.findById(inquerySeq);
		Inquery inquery = optional.get();
		inquery.setInqueryAnswer("ㅋㅋ ㅂㅅㅋㅋㅋ");
		inqueryRepo.save(inquery);
	}

	@Override
	public void answerRep(Long reportSeq) {
		// TODO Auto-generated method stub
		Optional<Report> optional = reportRepo.findById(reportSeq);
		Report report = optional.get();
		report.setReportAnswer("아 ㅋㅋ 알겠다고 ㅋㅋ");
		reportRepo.save(report);
	}
	
	
}
 





















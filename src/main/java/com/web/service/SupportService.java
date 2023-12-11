package com.web.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.Inquery;
import com.web.domain.Member;
import com.web.domain.Report;

public interface SupportService {
	public static final String INQUERY_IMAGE_REPO = "D:\\resource\\spring_boot\\workspace\\E1I4S\\src\\main\\resources\\static\\inqueryImgFolder";
	public static final String REPORT_IMAGE_REPO = "D:\\resource\\spring_boot\\workspace\\E1I4S\\src\\main\\resources\\static\\reportImgFolder";
	public void insertInquery(MultipartHttpServletRequest mul);
	public void getInqueryList(Model model, Inquery inquery, Pageable pageable, Member member);
	
	public void insertReport(MultipartHttpServletRequest mul);
	public void getReportList(Model model, Report report, Pageable pageable, Member member);
	
	public void find3List(Inquery inquery, Report report, Pageable pageable1, Pageable pageable2, Pageable pageable3, 
						  String searchKeyword1, String searchKeyword2, String searchKeyword3, Model model);
	
	public void getInqueryById(Long inquerySeq, Model model);
	public void getReportById(Long reportSeq, Model model);
	public void answerInq(Long inquerySeq);
	public void answerRep(Long reportSeq);
}
package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.domain.Inquery;
import com.web.domain.Member;
import com.web.domain.Report;
import com.web.service.SupportService;


@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	private SupportService supportService;
	
	//---------------------------------------------------------------
	
	@GetMapping("/report_admin")
	public String report_admin(Inquery inquery, Report report,
							   @Qualifier("pageable1") @PageableDefault(page = 0, size = 7,sort = "inquerySeq",direction = Sort.Direction.DESC) Pageable pageable1,
							   @Qualifier("pageable2") @PageableDefault(page = 0, size = 7,sort = "inquerySeq",direction = Sort.Direction.DESC) Pageable pageable2,
							   @Qualifier("pageable3") @PageableDefault(page = 0, size = 7,sort = "reportSeq",direction = Sort.Direction.DESC) Pageable pageable3,
							   Model model, String searchKeyword1, String searchKeyword2, String searchKeyword3){
		supportService.find3List(inquery, report, pageable1,pageable2,pageable3, searchKeyword1,searchKeyword2, searchKeyword3, model);
		return "admin/report_admin";
	}
	//---------------------------------------------------------------
	@GetMapping("/report_view_admin")
	public String report_view_admin(Long inquerySeq, Model model) {
		supportService.getInqueryById(inquerySeq, model);
		return "admin/report_view_admin";
	}
	@GetMapping("/report_view_admin2")
	public String report_view_admin2(Long reportSeq, Model model) {
		supportService.getReportById(reportSeq, model);
		return "admin/report_view2_admin";
	}
	//---------------------------------------------------------------
	@GetMapping("answer1")
	public String answer1(Long inquerySeq) {
		supportService.answerInq(inquerySeq);
		return "redirect:report_admin";
	}
	//---------------------------------------------------------------
	@GetMapping("answer2")
	public String answer2(Long reportSeq) {
		supportService.answerRep(reportSeq);
		return "redirect:report_admin";
	}
	//--------------------------------------------------------------- 
	//---------------------------------------------------------------
	
}

package com.web.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.Inquery;
import com.web.domain.Member;
import com.web.domain.Report;
import com.web.service.MemberService;
import com.web.service.SupportService;

@RequestMapping("/support")
@Controller
public class SupportController {
	
	@Autowired
	private SupportService supportService;
	@Autowired
	private MemberService ms;
	
	
	//---------------------------------------------------------------
	@GetMapping("/report_bug")
	public String report_bug(Principal principal, ModelMap modelMap){
		if(principal != null) {
			Member member = ms.memberInfo(principal);
			modelMap.addAttribute("member", member);
			return "support/report_bug";
		}
		return "login";
	}
	@GetMapping("/report_1_1") 
	public String report_1_1(Principal principal, ModelMap modelMap){
		if(principal != null) {
			Member member = ms.memberInfo(principal);
			modelMap.addAttribute("member", member);
			return "support/report_1_1";
		}
		return "login";
	}
	//---------------------------------------------------------------
	@PostMapping("/inquerySubmit")
	public String inquerySubmit(MultipartHttpServletRequest mul) {
		supportService.insertInquery(mul);
		return "redirect:/";
	}
	@GetMapping("/report_1_1_list") 
	public String report_1_1_list(Model model, Inquery inquery,
								  @PageableDefault(page = 0, size = 3,sort = "inquerySeq",direction = Sort.Direction.DESC)Pageable pageable,
								  Principal principal){
		Member member = ms.memberInfo(principal);
		supportService.getInqueryList(model, inquery, pageable, member);
		return "support/report_1_1_list";
	}
	//---------------------------------------------------------------
	@PostMapping("/reportSubmit")
	public String reportSubmit(MultipartHttpServletRequest mul) {
		supportService.insertReport(mul);
		return "redirect:/";
	}
	@GetMapping("/report_bug_list") 
	public String report_bug_list(Model model, Report report,
								  @PageableDefault(page = 0, size = 3,sort = "reportSeq",direction = Sort.Direction.DESC) Pageable pageable,
								  Principal principal){
		Member member = ms.memberInfo(principal);
		supportService.getReportList(model, report, pageable, member);
		return "support/report_bug_list";
	}
	//---------------------------------------------------------------
	@GetMapping("report_1_1_view")
	public String report_1_1_view(Long inquerySeq, Model model) {
		supportService.getInqueryById(inquerySeq, model);
		return "support/report_1_1_view";
	}
	@GetMapping("report_bug_view")
	public String report_bug_view(Long reportSeq, Model model) {
		supportService.getReportById(reportSeq, model);
		return "support/report_bug_view";
	}
	//---------------------------------------------------------------
}
 

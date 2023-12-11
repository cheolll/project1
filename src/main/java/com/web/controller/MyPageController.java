package com.web.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.domain.Member;
import com.web.persistence.MemberRepository;
import com.web.service.MemberService;

@Controller
public class MyPageController {

	@Autowired
	MemberService ms;
	
	private final MemberRepository memberRepo;
	private final PasswordEncoder passowrdEncoder;

	public MyPageController(MemberRepository memberRepo, PasswordEncoder passowrdEncoder) {
		this.memberRepo = memberRepo;
		this.passowrdEncoder = passowrdEncoder;
	}

	@GetMapping("/mypage/mypage")
	public void myPage() {

	}

	@GetMapping("/mypage/info")
	public void info(Principal principal, ModelMap modelMap) {
		Member member = ms.memberInfo(principal);
		System.out.println(member.toString());
		modelMap.addAttribute("member", member);
	}
	

	// 멤버 정보 infoUpdate 페이지에 띄우기
	@GetMapping("/mypage/infoUpdate")
	public String infoUpdate(Principal principal, ModelMap modelMap) {
		Member member = ms.memberInfo(principal);
		modelMap.addAttribute("member", member);
		return "mypage/infoUpdate";
	}

	// 멤버 정보 수정
	@PostMapping("/mypage/infoUpdateSuccess")
	public String infoUpdateSuccess(Member member, Model model) {
		Member newMember = ms.updateInfo(member);
		model.addAttribute("member", newMember);
		return "/mypage/infoUpdateSuccess";
	}

	// 비밀번호 수정
	@GetMapping("/mypage/infoPwdUpdate")
	public void infoPwdUpdate() {

	}

	// 수정완료
	@PostMapping("/mypage/infoPwdUpdate")
	public String infoPwdUpdateCom(@ModelAttribute("member") Member member, Model model) {
		Member setPw = ms.updatePw(member);
		model.addAttribute("member", setPw);
		return "/mypage/mypage";
	}

	// 회원 탈퇴
	@GetMapping("/mypage/deleteMember")
	public void deleteMember() {

	}

}

// 실풰
//	@GetMapping("/mypage/info")
//	public void info(Principal principal, ModelMap modelMap) {
//		String loginId = principal.getName();
//		Optional<Member> member = memberRepo.findById(loginId);
//		Member findMember = memberRepo.findById(loginId).get();
//		
//		System.out.println(findMember.toString());
//		modelMap.addAttribute("member", findMember);
//	}

//	@GetMapping("/mypage/info")
//	public void info(Principal principal, Model model) {
//		String name = principal.getName();
//		ms.memberInfo(name);
//	}
//	@GetMapping("/mypage/info")
//	public String info(@ModelAttribute("member") Member member, Model model) {
//		/*
//		 * if (member.getId() == null){ return "redirect:loginForm"; }
//		 */
//		
//		model.addAttribute("member", member);
//		return "/mypage/info";
//	}
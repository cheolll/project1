package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.Admin;
import com.web.domain.CharacterGuide;
import com.web.domain.Guide;
import com.web.domain.Member;
import com.web.service.CharacterService;
import com.web.service.GuideService;

@SessionAttributes("admin") // 관리자가 아니면 URL강제로 접근 못하게 session 추가
@RequestMapping("/guide")
@Controller
public class GuideController {
	
	@Autowired
	private GuideService guideService;
	
	@Autowired
	private CharacterService characterService;
	
	@ModelAttribute("admin")
	public Admin setAdmin() {
		return new Admin(); // 세션 등록
	}
	

	// 1. 기초가이드 보기
	@RequestMapping("/guide") 
	public String guide(@ModelAttribute("admin") Admin admin, Model model, Guide guide) { 
		guideService.getGuideList(model, guide);
		return "/guide/guide";
	}
	
	// 2. 캐릭터 소개 보기
	@RequestMapping("/character") 
	public String character(@ModelAttribute("admin") Admin admin, Model model, CharacterGuide characterGuide) {
		characterService.getCharacterList(model, characterGuide);
		return "/guide/character";
	}
	
	// 3. 캐릭터 상세 보기
	@RequestMapping("/characterView")
	public String characterView(@ModelAttribute("admin") Admin admin, Model model, CharacterGuide characterGuide) {
		characterService.getCharacter(characterGuide);
		return "/guide/characterView";
	}
	
	
	// 관리자 역할
	// 1. 가이드 등록 폼
	@GetMapping("/guideWriteForm") 
	public String guideWriteForm(@ModelAttribute("admin") Admin admin) {
		if(admin.getAdminId() != null) { // 로그인이 안되어 있으면 로그인 페이지로 이동(우선 지금은 조건 다르게줌)
			return "redirect:login";
		}
		return "/guide/guideWriteForm";
		
	}
	
	
	// 1-2. 가이드 등록
	@PostMapping("/guideInsert") 
	public String guideWriteForm(@ModelAttribute("admin") Admin admin, MultipartHttpServletRequest mul) {
		if(admin.getAdminId() != null) { // 로그인이 안되어 있으면 로그인 페이지로 이동
			return "redirect:login";
		}
		guideService.insertGuide(mul);
		return "redirect:guide"; // 가이드 글목록으로 가기
		
	}
	
	
	// 2.가이드 수정 폼
	@GetMapping("/guideUpdate")
	public String guideUpdate(@ModelAttribute("admin") Admin admin, Model model, Long guideNum) { // 이전에 있던 데이터를 가져올 수 있도록 함. 
		if(admin.getAdminId() != null) {
			return "redirect:login";
		}
		guideService.getGuide(guideNum, model);
		return "/guide/guideUpdateForm";
	}
	
	// 2-2. 가이드 수정 등록
	@PostMapping("/guideUpdate") // 기초가이드 수정
	public String guideUpdate(@ModelAttribute("admin") Admin admin, Guide guide) {
		if(admin.getAdminId() != null) {
			return "redirect:login";
		}
		
		guideService.updateGuide(guide);
		return "forward:guide";
	}
	
	
	
	 // 3. 가이드 삭제
	@GetMapping("/guideDelete") // 기초 가이드 삭제
	public String guideDelete(@ModelAttribute("admin") Admin admin, Guide guide) {
		if(admin.getAdminId() != null) {
			return "redirect:login";
		}
		
		guideService.deleteGuide(guide);
		return "forward:guide";
	}
	
	
	
	
}

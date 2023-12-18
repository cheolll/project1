package com.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.CharacterGuide;
import com.web.domain.Guide;
import com.web.domain.Member;
import com.web.service.CharacterService;
import com.web.service.GuideService;
import com.web.service.MemberService;

@RequestMapping("/guide")
@Controller
public class GuideController {
	
	@Autowired
	private GuideService guideService;
	
	@Autowired
	private CharacterService characterService;
	@Autowired
	private MemberService ms;
	
	// 1. 기초가이드 보기
	@RequestMapping("/guide") 
	public String guide(Model model, Guide guide, Principal principal, ModelMap modelMap) { 
		if(principal != null) {
			Member member = ms.memberInfo(principal);
			modelMap.addAttribute("member", member);
		} else {
			Member member = new Member();
			modelMap.addAttribute("member", member);
		}
		guideService.getGuideList(model, guide);
		return "/guide/guide";
	}
	
	// 2. 캐릭터 소개 보기
	@RequestMapping("/character") 
	public String character(Model model, Principal principal, ModelMap modelMap) {
		if(principal != null) {
			Member member = ms.memberInfo(principal);
			modelMap.addAttribute("member", member);
		} else {
			Member member = new Member();
			modelMap.addAttribute("member", member);
		}
		characterService.getCharacterList(model);
		return "/guide/character";
	}
	
	// 3. 캐릭터 상세 보기
	@RequestMapping("/characterView")
	public String characterView(Model model, Long characterGuideNum, Principal principal, ModelMap modelMap) {
		if(principal != null) {
			Member member = ms.memberInfo(principal);
			modelMap.addAttribute("member", member);
		}else {
			Member member = new Member();
			modelMap.addAttribute("member", member);
		}
		characterService.getCharacter(model, characterGuideNum);
		return "/guide/characterView";
	}
	
	// ****************************************************************************
	
	
	/* 관리자 로그인이 안 되어있으면 로그인 창으로 이동시키기*/
	// 관리자 역할 
	// <기초가이드>
	// 1. 가이드 등록 폼 
	@GetMapping("/guideWriteForm") 
	public String guideWriteForm(Principal principal, ModelMap modelMap) {
		Member member = ms.memberInfo(principal);
		modelMap.addAttribute("member", member);
		return "/guide/guideWriteForm";
	}
	
	
	// 1-2. 가이드 등록
	@PostMapping("/guideInsert") 
	public String guideInsert(MultipartHttpServletRequest mul) {
		guideService.insertGuide(mul);
		// 가이드 글목록으로 가기
		return "redirect:guide"; 
		
	}
	
	
	// 2.가이드 수정 폼
	@GetMapping("/guideUpdateForm")
	public String guideUpdateForm(Model model, Long guideNum, Principal principal, ModelMap modelMap) { 
		// 이전에 있던 데이터를 가져올 수 있도록 함. 
		Member member = ms.memberInfo(principal);
		modelMap.addAttribute("member", member);
		guideService.getGuide(guideNum, model);
		return "/guide/guideUpdateForm";
	}
	
	
	// 2-2. 가이드 수정 
	@PostMapping("/guideUpdate")
	public String guideUpdate(MultipartHttpServletRequest mul) {
		guideService.updateGuide(mul);
		return "redirect:guide";
	}
	
	
	
	 // 3. 가이드 삭제
	@GetMapping("/guideDelete")
	public String guideDelete(Guide guide) {
		guideService.deleteGuide(guide);
		return "forward:guide";
	}
	
	// ****************************************************************************
	
	// 관리자 역할 
	// <캐릭터 소개>
	// 1. 캐릭터 등록 폼
	@GetMapping("/characterWriteForm") 
	public String CharacterWriteForm(Principal principal, ModelMap modelMap) {
		Member member = ms.memberInfo(principal);
		modelMap.addAttribute("member", member);
		return "/guide/characterWriteForm";
		
	}
	
	// 1-2. 캐릭터 등록
	@PostMapping("/characterInsert") 
	public String characterInsert(MultipartHttpServletRequest mul) {
		characterService.insertCharacter(mul);
		return "redirect:character"; // 가이드 글목록으로 가기
		
	}
	
	
	
	// 2. 캐릭터 수정 폼 // 이전에 있던 데이터를 가져올 수 있도록 함. 
	@GetMapping("/characterUpdateForm")
	public String characterUpdateForm(Model model, Long characterGuideNum, Principal principal, ModelMap modelMap) {
		Member member = ms.memberInfo(principal);
		modelMap.addAttribute("member", member); 
		characterService.getCharacter(model, characterGuideNum);
		return "/guide/characterUpdateForm";
	}
	
	
	// 2-2. 캐릭터 수정 
	@PostMapping("/characterUpdate") 
	public String characterUpdate(MultipartHttpServletRequest mul) {
		characterService.updateCharacter(mul);
		return "redirect:character";
	}
	
	
	
	// 3. 캐릭터 삭제
	@GetMapping("/characterDelete") 
	public String characterDelete(CharacterGuide characterGuide) {
		characterService.deleteCharacter(characterGuide);
		return "forward:character";
	}
	
	
	
}

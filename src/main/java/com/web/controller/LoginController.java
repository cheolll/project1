package com.web.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.domain.Member;
import com.web.service.MemberService;


@Controller
public class LoginController {

	@Autowired
	MemberService ms;
	
	@GetMapping("/")
	public String index(Principal principal, ModelMap modelMap) {
		if(principal != null) {
			Member member = ms.memberInfo(principal);
			modelMap.addAttribute("member", member);
			return "index";
		}
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/logout")
	public void logout() {
	}
	
	
	
	
}

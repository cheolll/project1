package com.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.domain.Member;
import com.web.service.MemberService;

@RequestMapping("/join")
@Controller
public class JoinController {

	@Autowired
	MemberService ms;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/join")
	public void join() {
	}
	
	@GetMapping("/joinAgree")
	public void joinAgree() {
	}
	
	@PostMapping("/joinSuccess")
	public void joinSuccess(@ModelAttribute("member") Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		ms.joinMember(member);
	}
	
}

package com.web.service;

import java.security.Principal;

import com.web.domain.Member;

public interface MemberService {

	public void joinMember(Member member);

	public Member loginMember(Member member);
	
	public Member memberInfo(Principal principal);
	
	public Member updateInfo(Member member);
	
	public Member updatePw(Member member);
}
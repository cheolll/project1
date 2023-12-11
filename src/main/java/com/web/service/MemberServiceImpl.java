package com.web.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
import com.web.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepo;

	@Override
	public void joinMember(Member member) {
		memberRepo.save(member);
	}
	
	@Override
	public Member loginMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());

		if (findMember.isPresent()) {
			return findMember.get();
		} else {
			return null;
		}
	}
	
	
	// 시큐리티 name 값으로 멤버 정보 가져오기
	@Override
	public Member memberInfo(Principal principal){
		String id = principal.getName();
		Optional<Member> findMember = memberRepo.findById(id);
		return findMember.get();
	}
	
	@Override
	public Member updateInfo(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		
		findMember.setNickName(member.getNickName());
		findMember.setPhoneNumber(member.getPhoneNumber());
		findMember.setBirth(member.getBirth());
		findMember.setEmail(member.getEmail());
		findMember.setDomain(member.getDomain());
		findMember.setAddr(member.getAddr());
		memberRepo.save(findMember);
		
		Optional<Member> changeMember = memberRepo.findById(member.getId());
		
		return changeMember.get();
	}
	
	@Override
	public Member updatePw(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		
		findMember.setPassword(member.getPassword());
		memberRepo.save(findMember);
		
		Optional<Member> changeMember = memberRepo.findById(member.getId());
		
		return changeMember.get();
	}

}

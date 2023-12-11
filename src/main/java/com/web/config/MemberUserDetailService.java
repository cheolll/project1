package com.web.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
import com.web.persistence.MemberRepository;

@Service
public class MemberUserDetailService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<Member> optional = memberRepo.findById(id);
		
		if(!optional.isPresent()) {
			System.out.println("사용자 없음??");
			throw new UsernameNotFoundException(id + "사용자 없음!!");
		} else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
	}
	
	
	
}

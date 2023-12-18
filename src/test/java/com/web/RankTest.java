package com.web;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.web.domain.Member;
import com.web.persistence.MemberRepository;

@SpringBootTest
public class RankTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	
	@Test
	public void testInsert() {
		for(int i=0;i<100;i++) {
			// 회원 생성
			Member member = new Member();
			member.setId("test"+i);
			member.setPassword("qwer1234!");
			member.setName("테스트"+i);
			member.setNickName("WOW" + i);
			member.setPhoneNumber("010-2020-2020");
			member.setEmail("test1");
			member.setDomain("naver.com");
			member.setAddr("서울 강남구 가로수길 A");
//			member.setBirth(new Date());
			
			// 포인트 생성
			member.setPoint(Integer.parseInt(i+""));
			member.setTotalPoint(Integer.parseInt(i+""));
			memberRepo.save(member);
		}
		
	}
	
}

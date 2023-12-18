package com.web.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	// 주민번호로 조회
	@Query("SELECT m FROM Member m WHERE m.socialSecuNum like %:searchKeyword")
	List<Member> socialSecuNumCheck(@Param("searchKeyword") String searchKeyword);

	// 전화번호로 조회
	@Query("SELECT m FROM Member m WHERE m.phoneNumber like %:searchKeyword")
	List<Member> phoneNumberCheck(@Param("searchKeyword") String searchKeyword);

	// 랭킹 상위 3명
	List<Member> findTop3ByOrderByTotalPointDesc();

	// 누적 포인트 순으로 멤버 조회
	@Query(value = "SELECT RANK() OVER(ORDER BY total_point DESC) rank , tt.* FROM member tt", nativeQuery = true)
	List<Member> rankCheck();
	
	// 닉네임으로 조회
	Member findByNickName(String searchKeyword);

	// 이름, 주민번호, 전화번호로 아이디 찾기
	Member findByNameAndSocialSecuNumAndPhoneNumber(String name, String socialSecuNum, String phoneNumber);

	Member findByIdAndNameAndSocialSecuNumAndPhoneNumber(String id, String name, String socialSecuNum, String phoneNumber);
}

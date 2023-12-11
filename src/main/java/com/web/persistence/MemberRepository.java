package com.web.persistence;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String>{

	
}

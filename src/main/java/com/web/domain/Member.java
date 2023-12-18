package com.web.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter 
@Setter
@Entity
@Table(name = "MEMBER")
public class Member {

	
	@Id
	private String id;
	private String password;
	private String name;
	private String nickName;
	private String phoneNumber;
	private String socialSecuNum;	// 주민번호 전체
	private String email;
	private String domain;
	private String addr;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(columnDefinition = "number default 0")
	private int point;
	
	@Column(columnDefinition = "number default 0")
	private int totalPoint;
	
	private boolean enabled;
	
	
}

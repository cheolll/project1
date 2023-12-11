package com.web.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="ADMIN")
public class Admin {
   
   @Id
   @Column(name="ADMIN_ID")
   private String adminId; // 관리자 ID -- 기본키
   
   @Column(name="ADMIN_IMAGENAME", nullable = false)
   private  String adminImagename; // 관리자 사진
   
   @Column(name="ADMIN_PASSWORD", nullable = false)
   private  String adminPassword; // 관리자 비밀번호
   
   @Column(name="ADMIN_NICKNAME", nullable = false)
   private String adminNickName; // 관리자 닉네임
   
}
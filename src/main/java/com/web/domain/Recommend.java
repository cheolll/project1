package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "RECOMMEND")
public class Recommend {

    @Id
    @GeneratedValue
    @Column(name = "RECOMMEND")
    private Long recommend; //추천수(좋아요) 

    @Column(name="BOARD_SEQ")
    private Long boardSeq;
    
    @Column(name="USER_ID")
    private String userId;
}

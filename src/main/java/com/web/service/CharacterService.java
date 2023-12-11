package com.web.service;

import java.util.List;

import org.springframework.ui.Model;

import com.web.domain.CharacterGuide;

public interface CharacterService {
	
	public void getCharacterList(Model model, CharacterGuide characterGuide); // 캐릭터 목록

	public void insertCharacter(CharacterGuide characterGuide); // 캐릭터 등록

	public CharacterGuide getCharacter(CharacterGuide characterGuide); // 캐릭터 보기

	public void updateCharacter(CharacterGuide characterGuide); // 캐릭터 수정

	public void deleteCharacter(CharacterGuide characterGuide); // 캐릭터 삭제

}
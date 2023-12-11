package com.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.web.domain.CharacterGuide;
import com.web.domain.Guide;
import com.web.persistence.CharacterRepository;


@Service
public class CharacterServiceImpl implements CharacterService{
	
	@Autowired
	private CharacterRepository characterRepo;
	

	@Override
	public void getCharacterList(Model model, CharacterGuide characterGuide) {
		List<CharacterGuide> characterList =  (List<CharacterGuide>)characterRepo.findAll(); 
		model.addAttribute("characterList", characterList);
	}

	@Override
	public void insertCharacter(CharacterGuide characterGuide) {
		characterRepo.save(characterGuide);
	}

	@Override
	public CharacterGuide getCharacter(CharacterGuide characterGuide) {
		return characterRepo.findById(characterGuide.getCharacterGuideNum()).get();
	}

	@Override
	public void updateCharacter(CharacterGuide characterGuide) {
		
	}

	@Override
	public void deleteCharacter(CharacterGuide characterGuide) {
		
	}
	
	
	
}

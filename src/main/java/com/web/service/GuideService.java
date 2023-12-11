package com.web.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.Guide;

public interface GuideService {
	
	public static final String GUIDE_IMAGE_REPO ="D:\\resource\\spring_boot\\workspace\\E1I4S\\src\\main\\resources\\static\\guideImage";

	public void getGuideList(Model model, Guide guide); // 가이드 목록

	public void insertGuide(MultipartHttpServletRequest mul); // 가이드 등록

	public void getGuide(Long guideNum, Model model); // 가이드 보기

	public void updateGuide(Guide guide); // 가이드 수정

	public void deleteGuide(Guide guide); // 가이드 삭제
	
	public void updateGuideForm(Model model, Long GuideNum);
	
}

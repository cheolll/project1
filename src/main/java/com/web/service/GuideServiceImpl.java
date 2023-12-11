package com.web.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.Guide;
import com.web.domain.Inquery;
import com.web.persistence.GuideRepository;

@Service
public class GuideServiceImpl implements GuideService{

	@Autowired
	private GuideRepository guideRepo;
	
	@Override
	public void getGuideList(Model model, Guide guide){
		List<Guide> guideList = (List<Guide>)guideRepo.findAll(); 
		model.addAttribute("guideList", guideList);
	}
	
	@Override
	public void insertGuide(MultipartHttpServletRequest mul) {
		Guide guide = new Guide();
		guide.setGuideWriter(mul.getParameter("guideWriter"));
		guide.setGuideTitle(mul.getParameter("guideTitle"));
		guide.setGuideContents(mul.getParameter("guideContents"));
		
		MultipartFile file = mul.getFile("file");
		
		if(file.getSize() !=0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar calendar = Calendar.getInstance();
			String sysFileName = sdf.format(calendar.getTime());
			sysFileName += file.getOriginalFilename();
			File saveFile = new File(GUIDE_IMAGE_REPO + "/" + sysFileName); 
			guide.setGuideImageName(sysFileName);
			try {
				file.transferTo(saveFile);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			guide.setGuideImageName("nan");
		}
		guideRepo.save(guide); 
	}
	
	@Override
	public void getGuide(Long guideNum, Model model) {
		Guide guide = guideRepo.findById(guideNum).get();
		model.addAttribute(guide);
	}
	
	@Override
	public void updateGuide(Guide guide) {
		Guide findGuide = guideRepo.findById(guide.getGuideNum()).get(); // 수정하기 전의 본래의 내용 가져옴(?)
		
		findGuide.setGuideTitle(guide.getGuideTitle()); // 수정한 제목을 가져와서 세팅함.
		findGuide.setGuideContents(guide.getGuideContents()); // 수정한 내용을 가져와서 세팅함.
		guideRepo.save(guide); // DB에 반영시킴.
	}
	
	@Override
	public void deleteGuide(Guide guide) {
		guideRepo.deleteById(guide.getGuideNum()); 
	}

	@Override
	public void updateGuideForm(Model model, Long GuideNum) {
		Guide guide = guideRepo.findById(GuideNum).get();
		model.addAttribute("guide", guide);
	}
	
}

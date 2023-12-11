package com.web.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.PointShop;
import com.web.persistence.PointShopRepository;

@Service
public class PointShopServiceImpl implements PointShopSerivce {
	
	@Autowired
	private PointShopRepository pointShopRepo; //mapper

	@Override
	public void insertProduct(MultipartHttpServletRequest mul) {
		PointShop pointShop = new PointShop();
		pointShop.setProductName(mul.getParameter("productName"));
		pointShop.setProductContents(mul.getParameter("productContents"));
		pointShop.setProductPoint(Integer.parseInt(mul.getParameter("productPoint")));
		pointShop.setProductInventory(Integer.parseInt(mul.getParameter("productInventory")));
		
		MultipartFile file = mul.getFile("productImage");
		
		if(file.getSize() !=0) {
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-"); //지금 시간저장
	         Calendar calendar = Calendar.getInstance();
	         String sysFileName = sdf.format(calendar.getTime());
	         sysFileName += file.getOriginalFilename(); //시간 + 내가 올린 파일이름
	         File saveFile = new File(PRODUCT_IMAGE + "/" + sysFileName); 
	         pointShop.setProductImage(sysFileName);
	         try {
	            file.transferTo(saveFile);
	         } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	         }
	      } else {
	         pointShop.setProductImage("nan");
	      }
		
		
		pointShopRepo.save(pointShop);
	}
	
	
	
	@Override
	public void getProductList(Model model, PointShop pointShop, Pageable pageable) {
		Page<PointShop> productList = pointShopRepo.findAll(pageable); 
	      int nowPage = productList.getPageable().getPageNumber()+1;
	      int startPage = Math.max(nowPage - 4, 1);
	      int endPage = Math.min(nowPage + 5, productList.getTotalPages());
	      
	      model.addAttribute("productList", productList);
	      model.addAttribute("nowPage",nowPage);
	      model.addAttribute("startPage",startPage);
	      model.addAttribute("endPage",endPage);
	}



	@Override
	public void getProduct(Long productNumber, Model model) {
		// TODO Auto-generated method stub
		PointShop pointShop = pointShopRepo.findById(productNumber).get();
		model.addAttribute("product",pointShop);
	}

	@Override
	public void updateProduct(PointShop pointShopVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(PointShop pointShopVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void purchaseProduct(PointShop pointShopVO) {
		// TODO Auto-generated method stub
		
	}

	
}

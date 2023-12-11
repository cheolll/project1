package com.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.PointShop;
import com.web.service.PointShopSerivce;

@RequestMapping("pointshop")
@Controller
public class PointShopController {
	
	@Autowired
	private PointShopSerivce pointShopService;
	
	@GetMapping("/pointshop")
	public String pointShop(Model model, PointShop pointShop,
	         @PageableDefault(page = 0, size = 3,sort = "productNumber",direction = Sort.Direction.DESC) Pageable pageable){
		pointShopService.getProductList(model, pointShop, pageable);
	      return "/pointshop/pointshop";
	      
	}
	
	// 상품상세보기
	@GetMapping("/product")
	public String product(Long productNumber, Model model) {
		pointShopService.getProduct(productNumber, model);
		return "/pointshop/product";
	}
	
	// 상품수정하기
	@GetMapping("/productModify")
	public String productModify(PointShop pointShop) {
		return "/pointshop/productModify";
	}
	
	// 상품등록폼가기
	@GetMapping("/productInsert")
	public String productInsertForm() {
		return "/pointshop/productInsert";
	}
	
	// 상품등록하기
	@PostMapping("/insert")
	public String productInsert(MultipartHttpServletRequest mul) {
		pointShopService.insertProduct(mul);
		return "redirect:pointshop";
	}
	
//	//상품수정하기
//	@PostMapping("/modify")
//	public String productModify()
//	
	// 상품삭제하기
	@GetMapping("/productDelete")
	public String productDelete(PointShop pointShop) {
		return "/pointshop/pointshop";
	}
	
	
	
}
  
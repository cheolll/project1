package com.web.service;



import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.domain.PointShop;

public interface PointShopSerivce {
	
	public static final String PRODUCT_IMAGE ="D:\\resource\\spring_boot\\workspace\\E1I4S\\src\\main\\resources\\static\\productImage";
	
	public void getProductList(Model model, PointShop pointShop, Pageable pageable);

	void insertProduct(MultipartHttpServletRequest mul);
	
	void getProduct(Long productNumber, Model model);
	
	void updateProduct(PointShop pointShop);
	
	void deleteProduct(PointShop pointShop);
	
	void purchaseProduct(PointShop pointShop);
}

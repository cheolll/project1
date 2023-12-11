package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("news")
@Controller
public class NewsController {
	@GetMapping("/notice")
	public void notice() {
	} 
	@GetMapping("/event")
	public void event() {
	} 
	
}

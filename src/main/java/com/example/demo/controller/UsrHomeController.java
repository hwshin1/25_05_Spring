package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {
	@RequestMapping("/usr/home/main")
	public String main() {
		return "/usr/home/main";
	}
	
	@RequestMapping("/")
	public String showMain() {
		return "redirect:/usr/home/main";
	}
}

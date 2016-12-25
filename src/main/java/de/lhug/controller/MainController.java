package de.lhug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/main")
	public String showMain() {
		return "mainPage";
	}
	
	@RequestMapping("/")
	public String redirectMain() {
		return "redirect:/main";
	}
}

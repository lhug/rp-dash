package de.lhug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsController {

	@RequestMapping
	public String showGeneralNews() {
		return "news";
	}

	@RequestMapping("/mortal")
	public String showMortalNews() {
		return "news-mortal";
	}

	@RequestMapping("/immortal")
	public String showImmortalNews() {
		return "news-immortal";
	}
}

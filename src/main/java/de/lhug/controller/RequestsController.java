package de.lhug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/requests")
public class RequestsController {

	private static final String TARGET_PAGE = "requests";

	@RequestMapping
	public String showBackgroundsPage() {
		return TARGET_PAGE;
	}

}

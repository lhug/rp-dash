package de.lhug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rules")
public class RulesController {

	@RequestMapping
	public String showRules() {
		return "rules";
	}
}

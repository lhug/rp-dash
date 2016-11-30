package de.lhug.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.lhug.entities.Entry;
import de.lhug.service.InfoService;

@Controller
@RequestMapping("/backgrounds")
public class BackgroundsController {

	@Autowired
	private InfoService infoService;

	@RequestMapping("/info")
	public String showBackgroundsPage(Model model) {
		model.addAttribute("worldEntries", infoService.getWorldEntries());
		model.addAttribute("gameEntries", infoService.getGameEntries());
		model.addAttribute("timelineEntries", infoService.getTimelineEntries());
		model.addAttribute("newEntry", new Entry());
		return "info";
	}

	@RequestMapping(value = "/info/addEntry", method = RequestMethod.POST)
	public String addEntry(Entry entry, Model model, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			entry.setCreatedAt(new Date());
			infoService.storeEntry(entry);
			return "redirect:/backgrounds/info";
		} else {
			return "info";
		}
	}

	@RequestMapping("/characters")
	public String showCharacters() {
		return "characters";
	}
}

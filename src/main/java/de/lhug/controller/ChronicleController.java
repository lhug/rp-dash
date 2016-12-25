package de.lhug.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.lhug.entities.Chronicle;
import de.lhug.entities.Entry;
import de.lhug.entities.Entry.EntryType;
import de.lhug.service.InfoService;
import de.lhug.service.dao.ChronicleDao;

@Controller
@RequestMapping("/chronicle")
public class ChronicleController {

	private static final String TARGET_PAGE = "chronicle";

	@Autowired
	private InfoService infoService;

	@Autowired
	private ChronicleDao chronicleDao;
	
	@ModelAttribute
	public void initChronicle(@PathVariable Map<String, String> pathVariables, Model model) {
		String chronicleName = pathVariables.get("chronicleName");
		if (StringUtils.isNotBlank(chronicleName)){
			Chronicle chronicle = chronicleDao.getChronicle(chronicleName);
			model.addAttribute(chronicle);
		}
	}

	@RequestMapping
	public String showOverview(Model model) {
		model.addAttribute("chronicles", chronicleDao.getChronicles());
		model.addAttribute("newChronicle", new Chronicle());
		return "chronicleOverview";
	}

	@RequestMapping("/{chronicleName}")
	public String showChroniclesPage(Model model, @PathVariable String chronicleName) {
		model.addAttribute("newChronicleEntry", new Entry(EntryType.CHRONICLE));
		return TARGET_PAGE;
	}

	@RequestMapping(value = "/{chronicleName}/addEntry", method = RequestMethod.POST)
	public String addChronicleEntry(@ModelAttribute Chronicle chronicle, @ModelAttribute Entry entry,
			BindingResult bindingResult) {
		validateEntry(entry, bindingResult);
		if (bindingResult.hasErrors()) {
			return TARGET_PAGE;
		}
		entry.setType(EntryType.CHRONICLE);
		chronicle.addChronicleEntry(entry);
		infoService.storeEntry(entry);
		chronicleDao.updateChronicle(chronicle);
		return "redirect:/" + TARGET_PAGE + "/" + chronicle.getName();
	}

	private void validateEntry(Entry entry, BindingResult bindingResult) {
		if (entry.getCreatedAt() == null) {
			bindingResult.rejectValue("createdAt", "chronicle.entry.date");
		}
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String addChronicle(@ModelAttribute Chronicle chronicle) {
		String name = chronicle.getName();
		chronicleDao.insertChronicle(chronicle);
		return String.format("redirect:/chronicle/%s", name);
	}

}

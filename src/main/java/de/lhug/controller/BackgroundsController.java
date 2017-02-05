package de.lhug.controller;

import java.util.Collections;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.lhug.entities.Character;
import de.lhug.entities.Entry;
import de.lhug.entities.character.Abilities;
import de.lhug.entities.character.BackgroundValues;
import de.lhug.entities.enums.Dharma;
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
	public String showCharacters(Model model) {
		Character character = new Character();
		character.setChronicle("aChronicle");
		character.setName("Hun Po");
		model.addAttribute("characters", Collections.singletonList(character));
		return "characters";
	}

	@RequestMapping("/characters/{chronicle}/{character}")
	public String showCharacterFragment(Character c, Model m) {
		c.setUser("Pon3papa");
		c.setName("Xue Yuen Da");
		c.setChronicle("Faces of Dread");
		c.setExperience(25);
		c.setHunNature("Child");
		c.setPoNature("The Monkey");
		c.setDemeanor("Teacher");
		c.setBalance("Yin");
		c.setDirection("South");
		c.setWu("Phoenix Blossom");
		c.setYinChi(4);
		c.setYangChi(2);
		c.setWillpower(7);
		c.setDharmaScore(2);
		c.setPoValue(4);
		c.setDharma(Dharma.DEVIL_TIGER);
		
		Abilities a = new Abilities();
		a.getAthletics().put(StringUtils.EMPTY, 2);
		a.getDodge().put("none", 4);
		a.getLeadership().put("none", 3);
		a.getMartialArts().put("soft", 4);
		a.getCrafts().put("sewing", 2);
		a.getCrafts().put("chi-weaving", 4);
		a.getEnigmas().put("none", 3);
		c.setAbilities(a);
		BackgroundValues b = new BackgroundValues();
		c.setBackgroundValues(b);
		m.addAttribute(c);
		return "charSheet";
	}
	
	@RequestMapping(value = "/characters/{chronicle}/{character}", method=RequestMethod.POST)
	public String saveCharacter(Character character, Model model){
		character.getBalance();
		return "charSheet";
		
	}
}

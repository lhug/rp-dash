package de.lhug.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.annotation.Id;

import de.lhug.entities.character.Abilities;
import de.lhug.entities.character.Attributes;
import de.lhug.entities.character.BackgroundValues;
import de.lhug.entities.enums.Dharma;
import de.lhug.entities.enums.IAbility;
import de.lhug.entities.enums.Knowledges;
import de.lhug.entities.enums.Skills;
import de.lhug.entities.enums.Talents;
import lombok.Data;

@Data
public class Character {

	@Id
	private String id;

	private String chronicle;
	private String user;
	private String name;
	private String player;
	private String hunNature;
	private String poNature;
	private String demeanor;
	private String balance;
	private String direction;
	private String wu;
	private Dharma dharma;

	private List<Dharma> lostDharmas = new ArrayList<>();
	
	private int hunValue;
	private int poValue;
	private int yinChi;
	private int yangChi;
	private int willpower;
	private int dharmaScore;
	private int experience;
	
	private Abilities abilities;
	private BackgroundValues backgroundValues;
	private Attributes attributes;
	
	private Map<Date, String> history;

}

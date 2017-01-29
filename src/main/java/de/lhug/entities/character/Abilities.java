package de.lhug.entities.character;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Abilities {

	//Talents
	private Map<String, Integer> alertness = new HashMap<>();
	private Map<String, Integer> athletics = new HashMap<>();
	private Map<String, Integer> brawl = new HashMap<>();
	private Map<String, Integer> dodge = new HashMap<>();
	private Map<String, Integer> empathy = new HashMap<>();
	private Map<String, Integer> expression = new HashMap<>();
	private Map<String, Integer> intimidation = new HashMap<>();
	private Map<String, Integer> leadership = new HashMap<>();
	private Map<String, Integer> streetwise = new HashMap<>();
	private Map<String, Integer> subterfuge = new HashMap<>();
	
	//Skills
	private Map<String, Integer> acrobatics = new HashMap<>();
	private Map<String, Integer> animalKen = new HashMap<>();
	private Map<String, Integer> archery = new HashMap<>();
	private Map<String, Integer> crafts = new HashMap<>();
	private Map<String, Integer> etiquette = new HashMap<>();
	private Map<String, Integer> martialArts = new HashMap<>();
	private Map<String, Integer> meditation = new HashMap<>();
	private Map<String, Integer> melee = new HashMap<>();
	private Map<String, Integer> performance = new HashMap<>();
	private Map<String, Integer> portents = new HashMap<>();
	private Map<String, Integer> ride = new HashMap<>();
	private Map<String, Integer> stealth = new HashMap<>();
	private Map<String, Integer> survival = new HashMap<>();
	private Map<String, Integer> torture = new HashMap<>();
	
	//knowledges
	private Map<String, Integer> academics = new HashMap<>();
	private Map<String, Integer> enigmas = new HashMap<>();
	private Map<String, Integer> investigation = new HashMap<>();
	private Map<String, Integer> law = new HashMap<>();
	private Map<String, Integer> linguistics = new HashMap<>();
	private Map<String, Integer> medicine = new HashMap<>();
	private Map<String, Integer> occult = new HashMap<>();
	private Map<String, Integer> politics = new HashMap<>();
	private Map<String, Integer> rituals = new HashMap<>();
	private Map<String, Integer> science = new HashMap<>();
	
	private Map<String, Integer> secondary = new HashMap<>();
}

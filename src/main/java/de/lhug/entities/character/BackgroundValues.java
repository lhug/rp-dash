package de.lhug.entities.character;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class BackgroundValues {

	private Map<String, Integer> merits = new HashMap<>();
	private Map<String, Integer> flaws = new HashMap<>();
	private Map<String, Integer> backgrounds = new HashMap<>();

	private Map<String, Integer> secondary = new HashMap<>();
	
	public void addMerit(String name, int value) {
		merits.put(name, value);
	}
	
	public void addFlaw(String name, int value) {
		flaws.put(name, value);
	}
	
	public void addBackground(String name, int value) {
		backgrounds.put(name, value);
	}
	
	public void addSecondarySkill(String name, int value) {
		secondary.put(name, value);
	}
}

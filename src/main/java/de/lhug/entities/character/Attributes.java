package de.lhug.entities.character;

import java.util.List;

import de.lhug.entities.enums.DemonCharacteristic;
import lombok.Data;

@Data
public class Attributes {

	private int strength;
	private int dexterity;
	private int stamina;
	
	private int charisma;
	private int manipulation;
	private int appearance;
	
	private int perception;
	private int intelligence;
	private int wits;

	private List<DemonCharacteristic> demonCharacteristics;
	private List<String> derangements;
}

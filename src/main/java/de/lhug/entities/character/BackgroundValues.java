package de.lhug.entities.character;

import java.util.Map;

import lombok.Data;

@Data
public class BackgroundValues {

	private Map<String, Integer> merits;
	private Map<String, Integer> flaws;
	private Map<String, Integer> backgrounds;
}

package de.lhug.entities.enums;

public enum Dharma {
	
	DEVIL_TIGER("dharma.tiger"), THRASHING_DRAGON("dharma.dragon"), RESPLENDENT_CRANE("dharma.crane"), THOUSAND_WHISPERS("dharma.centipede"), SHADOW_SONG("dharma.flower");

	private final String name;
	
	private Dharma(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

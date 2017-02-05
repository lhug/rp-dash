package de.lhug.entities.enums;

public enum Skills implements IAbility {

	ACROBATICS("abilities.acrobatics"), ANIMAL_KEN("abilities.animalKen"), ARCHERY("abilities.archery"), CRATFS("abilities.crafts"), ETIQUETTE(
					"abilities.etiquette"), MARTIAL_ARTS("abilities.martialArts"), MEDITATION("abilities.meditation"), MELEE("abilities.melee"), PERFORMANCE(
									"abilities.performance"), PORTENTS("abilities.portents"), RIDE("abilities.ride"), STEALTH(
											"abilities.stealth"), SURVIVAL("abilities.survival"), TORTURE("abilities.torture");

	final String messageKey;

	private Skills(String messageKey) {
		this.messageKey = messageKey;
	}

	@Override
	public String getMessageKey() {
		return messageKey;
	}
}

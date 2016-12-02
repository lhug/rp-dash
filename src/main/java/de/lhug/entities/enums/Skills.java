package de.lhug.entities.enums;

public enum Skills implements IAbility {

	ACROBATICS("skills.acrobatics"), ANIMAL_KEN("skills.animalKen"), ARCHERY("skills.archery"), CRATFS("skills.crafts",
			true), ETIQUETTE(
					"skills.etiquette"), MARTIAL_ARTS("skills.martialArts",
							true), MEDITATION("skills.meditation"), MELEE("skills.melee", true), PERFORMANCE(
									"skills.performance",
									true), PORTENTS("skills.portents"), RIDE("skills.ride"), STEALTH(
											"skills.stealth"), SURVIVAL("skills.survival"), TORTURE("skills.torture");

	final String messageKey;
	final boolean preSpecialized;

	private Skills(String messageKey) {
		this(messageKey, false);
	}

	private Skills(String messageKey, boolean preSpecialized) {
		this.messageKey = messageKey;
		this.preSpecialized = preSpecialized;
	}

	@Override
	public String getMessageKey() {
		return messageKey;
	}

	@Override
	public boolean isPreSpecialized() {
		return preSpecialized;
	}
}

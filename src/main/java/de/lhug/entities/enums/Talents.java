package de.lhug.entities.enums;

public enum Talents implements IAbility {

	ALERTNESS("talents.alertness"), ATHLETICS("talents.athletics"), BRAWL("talents.brawl"), DODGE(
			"talents.dodge"), EMPATHY("talents.empathy"), EXPRESSION("talents.expression"), INTIMIDATION(
					"talents.intimidation"), LEADERSHIP(
							"talents.leadership"), STREETWISE("talents.streetwise"), SUBTERFUGE("talents.subterfuge");

	private final String messageKey;
	private final boolean preSpecialized;

	private Talents(String messageKey) {
		this(messageKey, false);
	}

	private Talents(String messageKey, boolean preSpecialized) {
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

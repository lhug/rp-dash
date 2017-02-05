package de.lhug.entities.enums;

public enum Talents implements IAbility {

	ALERTNESS("abilities.alertness"), ATHLETICS("abilities.athletics"), BRAWL("abilities.brawl"), DODGE(
			"abilities.dodge"), EMPATHY("abilities.empathy"), EXPRESSION("abilities.expression"), INTIMIDATION(
					"abilities.intimidation"), LEADERSHIP(
							"abilities.leadership"), STREETWISE("abilities.streetwise"), SUBTERFUGE("abilities.subterfuge");

	private final String messageKey;

	private Talents(String messageKey) {
		this.messageKey = messageKey;
	}

	@Override
	public String getMessageKey() {
		return messageKey;
	}
}

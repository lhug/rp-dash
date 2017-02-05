package de.lhug.entities.enums;

public enum Knowledges implements IAbility {
	ACADEMICS("abilities.academics"), ENIGMAS("abilities.enigmas"), INVESTIGATION("abilities.investigation"), LAW(
			"abilities.law"), LINGUISTICS("abilities.linguistics"), MEDICINE("abilities.medicine"), OCCULT(
					"abilities.occult"), POLITICS(
							"abilities.politics"), RITUALS("abilities.rituals"), SCIENCE("abilities.science");

	private final String messageKey;

	private Knowledges(String messageKey) {
		this.messageKey = messageKey;
	}

	@Override
	public String getMessageKey() {
		return messageKey;
	}
}

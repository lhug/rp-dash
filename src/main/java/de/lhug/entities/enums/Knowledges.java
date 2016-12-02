package de.lhug.entities.enums;

public enum Knowledges implements IAbility {
	ACADEMICS("knowledges.academics"), ENIGMAS("knowledges.enigmas"), INVESTIGATION("knowledges.investigation"), LAW(
			"knowledges.law", true), LINGUISTICS("knowledges.linguistics"), MEDICINE("knowledges.medicine"), OCCULT(
					"knowledges.occult"), POLITICS(
							"knowledges.politics"), RITUALS("knowledges.rituals"), SCIENCE("knowledges.science", true);

	private final String messageKey;
	private final boolean preSpecialized;

	private Knowledges(String messageKey) {
		this(messageKey, false);
	}

	private Knowledges(String messageKey, boolean preSpecialized) {
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

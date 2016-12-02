package de.lhug.entities.enums;

public enum DemonCharacteristic {
	CLAWS("+2 a"), DEMON_ARMOR("+1 s"), DEMON_WEAPON("+0 a"), EXTRA_ARMS("+2 d"), FOULNESS("-1 dp"), HORROR(
			"+0 delirium"), HOST("+1 aStr"), HUGE_SIZE("+0 tall"), MAW(
					"+3 aStr"), SPIKES("+0 aStr"), TAIL("+1 aStr"), THIRD_EYE("+1 per"), WINGS("+0 flight");
	private final String effect;

	private DemonCharacteristic(String effect) {
		this.effect = effect;
	}

	public String getEffect() {
		return effect;
	}
}

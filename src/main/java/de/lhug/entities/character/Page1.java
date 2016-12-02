package de.lhug.entities.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.lhug.entities.enums.DemonCharacteristic;
import de.lhug.entities.enums.Knowledges;
import de.lhug.entities.enums.Skills;
import de.lhug.entities.enums.Talents;
import de.lhug.entities.enums.disciplines.IDiscipline;

public class Page1 {

	private Map<String, Integer> physical;
	private Map<String, Integer> social;
	private Map<String, Integer> mental;
	private List<Skills> skills = new ArrayList<>();
	private List<Talents> talents = new ArrayList<>();
	private List<Knowledges> knowledges = new ArrayList<>();

	private List<IDiscipline> disciplines = new ArrayList<>();

	private int dharma;
	private int hun;
	private int po;
	private int willpower;
	private int yin;
	private int yang;

	private List<String> backgrounds = new ArrayList<>();
	private Map<String, Integer> guanxi = new HashMap<>();
	private List<DemonCharacteristic> demonCharacteristics = new ArrayList<>();

	public Page1() {
		physical = makePhysical();
		social = makeSocial();
		mental = makeMental();
	}

	private Map<String, Integer> makePhysical() {
		Map<String, Integer> result = new HashMap<>();
		result.put("attributes.strength", 1);
		result.put("attributes.dexterity", 1);
		result.put("attributes.stamina", 1);
		return result;
	}

	private Map<String, Integer> makeSocial() {
		Map<String, Integer> result = new HashMap<>();
		result.put("social.charisma", 1);
		result.put("social.manipulation", 1);
		result.put("social.appearance", 1);
		return result;
	}

	private Map<String, Integer> makeMental() {
		Map<String, Integer> result = new HashMap<>();
		result.put("mental.perception", 1);
		result.put("mental.intelligence", 1);
		result.put("mental.wits", 1);
		return result;
	}

	public Map<String, Integer> getPhysical() {
		return physical;
	}

	public void setPhysical(Map<String, Integer> physical) {
		this.physical = physical;
	}

	public Map<String, Integer> getSocial() {
		return social;
	}

	public void setSocial(Map<String, Integer> social) {
		this.social = social;
	}

	public Map<String, Integer> getMental() {
		return mental;
	}

	public void setMental(Map<String, Integer> mental) {
		this.mental = mental;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public List<Talents> getTalents() {
		return talents;
	}

	public void setTalents(List<Talents> talents) {
		this.talents = talents;
	}

	public List<Knowledges> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<Knowledges> knowledges) {
		this.knowledges = knowledges;
	}

	public List<IDiscipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<IDiscipline> disciplines) {
		this.disciplines = disciplines;
	}

	public int getDharma() {
		return dharma;
	}

	public void setDharma(int dharma) {
		this.dharma = dharma;
	}

	public int getHun() {
		return hun;
	}

	public void setHun(int hun) {
		this.hun = hun;
	}

	public int getPo() {
		return po;
	}

	public void setPo(int po) {
		this.po = po;
	}

	public int getWillpower() {
		return willpower;
	}

	public void setWillpower(int willpower) {
		this.willpower = willpower;
	}

	public int getYin() {
		return yin;
	}

	public void setYin(int yin) {
		this.yin = yin;
	}

	public int getYang() {
		return yang;
	}

	public void setYang(int yang) {
		this.yang = yang;
	}

	public List<String> getBackgrounds() {
		return backgrounds;
	}

	public void setBackgrounds(List<String> backgrounds) {
		this.backgrounds = backgrounds;
	}

	public Map<String, Integer> getGuanxi() {
		return guanxi;
	}

	public void setGuanxi(Map<String, Integer> guanxi) {
		this.guanxi = guanxi;
	}

	public List<DemonCharacteristic> getDemonCharacteristics() {
		return demonCharacteristics;
	}

	public void setDemonCharacteristics(List<DemonCharacteristic> demonCharacteristics) {
		this.demonCharacteristics = demonCharacteristics;
	}
}

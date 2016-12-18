package de.lhug.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import de.lhug.entities.enums.Dharma;

public class Character {

	@Id
	private String id;

	private String chronicle;
	private String user;
	private String name;
	private String player;
	private String hunNature;
	private String poNature;
	private String demeanor;
	private String balance;
	private String direction;
	private String wu;
	private Dharma dharma;
	private int dharmaScore;

	private List<Dharma> lostDharmas = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChronicle() {
		return chronicle;
	}

	public void setChronicle(String chronicle) {
		this.chronicle = chronicle;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getHunNature() {
		return hunNature;
	}

	public void setHunNature(String hunNature) {
		this.hunNature = hunNature;
	}

	public String getPoNature() {
		return poNature;
	}

	public void setPoNature(String poNature) {
		this.poNature = poNature;
	}

	public String getDemeanor() {
		return demeanor;
	}

	public void setDemeanor(String demeanor) {
		this.demeanor = demeanor;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getWu() {
		return wu;
	}

	public void setWu(String wu) {
		this.wu = wu;
	}

	public List<Dharma> getLostDharmas() {
		return lostDharmas;
	}

	public void setLostDharmas(List<Dharma> lostDharmas) {
		this.lostDharmas = lostDharmas;
	}

	public void setDharma(Dharma dharma) {
		this.dharma = dharma;
	}

	public Dharma getDharma() {
		return dharma;
	}

	public int getDharmaScore() {
		return dharmaScore;
	}

	public void setDharmaScore(int dharmaScore) {
		this.dharmaScore = dharmaScore;
	}

}

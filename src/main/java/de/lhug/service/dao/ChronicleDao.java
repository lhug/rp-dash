package de.lhug.service.dao;

import java.util.List;

import de.lhug.entities.Chronicle;

public interface ChronicleDao {

	Chronicle getChronicle(String name);

	void updateChronicle(Chronicle chronicle);

	void deleteChronicle(String name);

	List<Chronicle> getChronicles();

	void insertChronicle(Chronicle chronicle);
}
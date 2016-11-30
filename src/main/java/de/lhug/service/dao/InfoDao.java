package de.lhug.service.dao;

import java.util.List;

import de.lhug.entities.Entry;
import de.lhug.entities.Entry.EntryType;

public interface InfoDao {

	void storeEntry(Entry entry);

	List<Entry> getEntries(EntryType... types);

	void deleteEntry(String topic);

}
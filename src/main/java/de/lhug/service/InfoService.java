package de.lhug.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import de.lhug.entities.Entry;
import de.lhug.entities.Entry.EntryType;
import de.lhug.service.dao.InfoDao;

@Service
public class InfoService {

	@Autowired
	private InfoDao infoDao;

	public List<Entry> getGameEntries() {
		return infoDao.getEntries(EntryType.GAME);
	}

	public List<Entry> getWorldEntries() {
		return infoDao.getEntries(EntryType.WORLD);
	}

	public List<Entry> getTimelineEntries() {
		return infoDao.getEntries(EntryType.TIMELINE);
	}

	public void storeEntry(Entry entry) {
		Assert.notNull(entry);
		infoDao.storeEntry(entry);
	}

}

package de.lhug.service;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.lhug.entities.Entry;
import de.lhug.entities.Entry.EntryType;
import de.lhug.service.dao.InfoDao;
import de.lhug.utils.EntryBuilder;

@RunWith(MockitoJUnitRunner.class)
public class InfoServiceTest {

	@Mock
	private InfoDao infoDao;

	@InjectMocks
	private InfoService sut;

	@Before
	public void setUp() {
	}

	@Test
	public void getGameEntries() {
		sut.getGameEntries();

		verify(infoDao).getEntries(EntryType.GAME);
	}

	@Test
	public void getWorldEntries() {
		sut.getWorldEntries();

		verify(infoDao).getEntries(EntryType.WORLD);
	}

	@Test
	public void getTimelineEntries() {
		sut.getTimelineEntries();

		verify(infoDao).getEntries(EntryType.TIMELINE);
	}

	@Test
	public void storeEntry() {
		Entry entry = EntryBuilder.entry(EntryType.GAME, "TestTopic");

		sut.storeEntry(entry);

		verify(infoDao).storeEntry(entry);
	}

	@Test(expected = IllegalArgumentException.class)
	public void storeInvalidEntry() {
		sut.storeEntry(null);
	}
}

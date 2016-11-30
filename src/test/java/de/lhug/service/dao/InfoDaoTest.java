package de.lhug.service.dao;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.lhug.entities.Entry;
import de.lhug.entities.Entry.EntryType;
import de.lhug.utils.EntryBuilder;
import de.lhug.utils.MongoUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = InfoDaoTest.TestConfiguration.class)
@EnableAutoConfiguration
@TestPropertySource(properties = { "spring.data.mongodb.port=27019" })
public class InfoDaoTest {

	public static class TestConfiguration {
		@Bean
		public InfoDao infoDao() {
			return new InfoDaoImpl();
		}
	}

	@Autowired
	private InfoDao sut;

	@Autowired
	private MongoOperations mongoOperations;

	@Before
	public void setUp() {
		Entry e1 = EntryBuilder.entry(EntryType.WORLD, "WorldEntry");
		Entry e2 = EntryBuilder.entry(EntryType.TIMELINE, "TimelineEntry");

		mongoOperations.insertAll(Arrays.asList(e1, e2));
	}

	@After
	public void tearDown() {
		MongoUtils.clean(mongoOperations);
	}

	@Test
	public void storeGameEntry() {
		Entry entry = EntryBuilder.entry(EntryType.GAME, "GameEntry");
		assertThat(entry.getId(), is(nullValue()));
		assertThat(entry.getType(), is(EntryType.GAME));

		sut.storeEntry(entry);

		List<Entry> result = mongoOperations.find(Query.query(Criteria.where("type").is(EntryType.GAME)), Entry.class);

		assertThat(result, hasSize(1));
		assertThat(result, hasItem(allOf(
				hasProperty("id", not(nullValue())),
				hasProperty("type", is(EntryType.GAME)),
				hasProperty("topic", is("GameEntry")))));
	}

	@Test
	public void fetchEntriesSingleType() {
		List<Entry> result = sut.getEntries(EntryType.WORLD);

		assertThat(result, is(not(empty())));
		assertThat(result, everyItem(hasProperty("type", is(EntryType.WORLD))));
	}

	@Test
	public void fetchEntriesMiltipleType() {
		List<Entry> result = sut.getEntries(EntryType.WORLD, EntryType.TIMELINE);

		assertThat(result, is(not(empty())));
		assertThat(result, everyItem((hasProperty("type", either(is(EntryType.WORLD)).or(is(EntryType.TIMELINE))))));
	}

	@Test
	public void deleteEntry() {
		sut.deleteEntry("TimelineEntry");

		List<Entry> result = mongoOperations.findAll(Entry.class);

		assertThat(result, not(hasItem(hasProperty("topic", is("TimelineEntry")))));
	}
}

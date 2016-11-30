package de.lhug.service.dao;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

import de.lhug.entities.Chronicle;
import de.lhug.entities.Entry;
import de.lhug.entities.Entry.EntryType;
import de.lhug.utils.EntryBuilder;
import de.lhug.utils.MongoUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ChronicleDaoTest.TestConfiguration.class)
@EnableAutoConfiguration
@TestPropertySource(properties = { "spring.data.mongodb.port=27019" })
public class ChronicleDaoTest {

	public static class TestConfiguration {

		@Bean
		public ChronicleDao chronicleDao() {
			return new ChronicleDaoImpl();
		}

		@Bean
		public InfoDao infoDao() {
			return mock(InfoDao.class);
		}
	}

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private ChronicleDao sut;

	@Autowired
	private InfoDao infoDao;

	private Chronicle c1, c2;
	private Entry e1, e2, e3;

	@Before
	public void setUp() {
		c1 = new Chronicle("chronicle_1");
		c2 = new Chronicle("chronicle_2");
		e1 = EntryBuilder.entry(EntryType.CHRONICLE, "Topic_1");
		e2 = EntryBuilder.entry(EntryType.CHRONICLE, "Topic_2");
		e3 = EntryBuilder.entry(EntryType.CHRONICLE, "Topic_3");

		c1.addChronicleEntry(e1);
		c2.addChronicleEntry(e3);

		mongoOperations.insertAll(Arrays.asList(c1, c2, e1, e2, e3));
	}

	@After
	public void tearDown() {
		MongoUtils.clean(mongoOperations);
	}

	@Test
	public void getChronicle() {
		Chronicle result = sut.getChronicle("chronicle_1");

		assertThat(result.getName(), is("chronicle_1"));
		List<Entry> chronicleEntries = result.getChronicleEntries();
		assertThat(chronicleEntries, hasSize(1));
		assertThat(chronicleEntries, hasItem(allOf(
				hasProperty("type", is(EntryType.CHRONICLE)),
				hasProperty("topic", is("Topic_1")))));
	}

	@Test
	public void updateChronicle() {
		c1.addChronicleEntry(e2);

		sut.updateChronicle(c1);

		Chronicle result = mongoOperations.findOne(Query.query(Criteria.where("name").is("chronicle_1")),
				Chronicle.class);

		assertThat(result.getName(), is("chronicle_1"));
		assertThat(result.getChronicleEntries(), hasSize(2));
		assertThat(result.getChronicleEntries(), everyItem(allOf(
				hasProperty("type", is(EntryType.CHRONICLE)),
				hasProperty("topic", either(is("Topic_1")).or(is("Topic_2"))))));
	}

	@Test
	public void deleteChronicle() {
		sut.deleteChronicle("chronicle_2");

		Chronicle chronicle = mongoOperations.findOne(Query.query(Criteria.where("name").is("chronicle_2")),
				Chronicle.class);
		assertThat(chronicle, is(nullValue()));

		verify(infoDao).deleteEntry(eq("Topic_3"));
	}

	@Test
	public void getChronicles() {
		List<Chronicle> expected = mongoOperations.findAll(Chronicle.class);

		List<Chronicle> result = sut.getChronicles();

		assertThat(result.size(), equalTo(expected.size()));
	}
}

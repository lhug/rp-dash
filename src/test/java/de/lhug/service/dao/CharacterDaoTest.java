package de.lhug.service.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CharacterDaoTest.TestConfiguration.class)
@EnableAutoConfiguration
@TestPropertySource(properties = { "spring.data.mongodb.port=27019" })
public class CharacterDaoTest {

	static class TestConfiguration {
	}

	@Test
	public void fetchCharactersByUsername() {
		fail();
	}

	@Test
	public void upsertCharacter() {
		fail();
	}

	@Test
	public void deleteCharacter() {
		fail();
	}
}

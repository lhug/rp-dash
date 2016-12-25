package de.lhug.controller;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.lhug.entities.Entry;
import de.lhug.service.InfoService;
import de.lhug.utils.UtilityClass;

@RunWith(MockitoJUnitRunner.class)
public class BackgroundsControllerTest {

	@InjectMocks
	private BackgroundsController sut;

	@Mock
	private InfoService infoService;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(sut).setViewResolvers(UtilityClass.viewResolver()).build();
	}

	@Test
	public void showBackgroundsPage() throws Exception {
		String url = "/backgrounds/info";

		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("worldEntries", "gameEntries", "timelineEntries", "newEntry"))
				.andExpect(view().name("info"));
		
		verify(infoService).getGameEntries();
		verify(infoService).getTimelineEntries();
		verify(infoService).getWorldEntries();
	}
	
	@Test
	public void showAddEntryFragment() throws Exception {
		String url = "/backgrounds/info/addEntry";
		
		mockMvc.perform(post(url).flashAttr("entry", new Entry()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/backgrounds/info"));
		
		verify(infoService).storeEntry(any(Entry.class));
	}
	
	@Test
	public void showCharactersPage() throws Exception {
		String url = "/backgrounds/characters";

		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(model().attribute("characters", isA(List.class)))
				.andExpect(model().attribute("characters", IsCollectionWithSize.hasSize(1)))
				.andExpect(view().name("characters"));
	}
	
	@Test
	public void showCharacterSheetFragment() throws Exception {
		String url = "/backgrounds/characters/chronicle/character";
		
		mockMvc.perform(get(url))	
			.andExpect(status().isOk())
			.andExpect(view().name("fragments/charSheet"));
	}
}

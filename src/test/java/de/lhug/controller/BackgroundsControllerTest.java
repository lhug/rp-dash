package de.lhug.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.lhug.controller.BackgroundsController;
import de.lhug.utils.UtilityClass;

@RunWith(MockitoJUnitRunner.class)
public class BackgroundsControllerTest {

	@InjectMocks
	private BackgroundsController sut;

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
				.andExpect(view().name("info"));
	}

	@Test
	public void showCharactersPage() throws Exception {
		String url = "/backgrounds/characters";

		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(view().name("characters"));
	}
}

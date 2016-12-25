package de.lhug.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.lhug.controller.MainController;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private MainController sut;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
	}

	@Test
	public void showMain() throws Exception {
		String url = "/main";

		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(view().name("mainPage"));
	}
	
	@Test
	public void redirectMain() throws Exception {
		String url = "/";
		
		mockMvc.perform(get(url))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/main"));
	}
}

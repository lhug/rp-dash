package de.lhug.controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.lhug.entities.Chronicle;
import de.lhug.entities.Entry;
import de.lhug.entities.Entry.EntryType;
import de.lhug.service.InfoService;
import de.lhug.service.dao.ChronicleDaoImpl;
import de.lhug.utils.UtilityClass;

@RunWith(MockitoJUnitRunner.class)
public class ChronicleControllerTest {

	@InjectMocks
	private ChronicleController sut;

	@Mock
	private InfoService infoService;

	@Mock
	private ChronicleDaoImpl chronicleDao;

	@Captor
	private ArgumentCaptor<Entry> entryCaptor;

	@Captor
	private ArgumentCaptor<Chronicle> chronicleCaptor;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(sut).setViewResolvers(UtilityClass.viewResolver()).build();
		when(chronicleDao.getChronicles()).thenReturn(Collections.singletonList(new Chronicle()));
		when(chronicleDao.getChronicle(anyString())).thenAnswer((i) -> new Chronicle(i.getArgumentAt(0, String.class)));
	}

	@Test
	public void showChronicles() throws Exception {
		String url = "/chronicle";

		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(model().attribute("chronicles", everyItem(isA(Chronicle.class))))
				.andExpect(model().attribute("newChronicle", isA(Chronicle.class)))
				.andExpect(view().name("chronicleOverview"));

		verify(chronicleDao).getChronicles();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void addNewEntry() throws Exception {
		String url = "/chronicle/chronicleName/addEntry";

		mockMvc.perform(postRequest(url))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/chronicle/chronicleName"));

		verify(infoService).storeEntry(entryCaptor.capture());
		verify(chronicleDao).updateChronicle(chronicleCaptor.capture());

		Entry result = entryCaptor.getValue();
		assertThat(result, allOf(
				hasProperty("type", is(EntryType.CHRONICLE)),
				hasProperty("createdAt", is(new Date("09/11/2001")))));

		Chronicle chronicle = chronicleCaptor.getValue();
		assertThat(chronicle.getEntries(), hasSize(1));
	}

	private MockHttpServletRequestBuilder postRequest(String url) {
		return post(url).param("createdAt", "09/11/2001").param("content", "CONTENT").param("type", "CHRONICLE");
	}

	@Test
	public void addNewEntryUnsuccessfully() throws Exception {
		String url = "/chronicle/chronicleName/addEntry";

		mockMvc.perform(post(url).param("content", "CONTENT"))
				.andExpect(status().isOk())
				.andExpect(view().name("chronicle"));

		verify(infoService, never()).storeEntry(any(Entry.class));
	}

	@Test
	public void showChronicle() throws Exception {
		String url = "/chronicle/chronicleName";

		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(model().attribute("chronicle", hasProperty("name", is("chronicleName"))))
				.andExpect(model().attribute("newChronicleEntry", isA(Entry.class)))
				.andExpect(model().attribute("newChronicleEntry", hasProperty("type", is(EntryType.CHRONICLE))));

		verify(chronicleDao).getChronicle("chronicleName");
	}

	@Test
	public void addChronicle() throws Exception {
		String url = "/chronicle/new";

		mockMvc.perform(post(url).param("beginDate", "09/11/2001").param("name", "chronicleName"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/chronicle/chronicleName"));

		verify(chronicleDao).insertChronicle(chronicleCaptor.capture());

		Chronicle result = chronicleCaptor.getValue();
		assertThat(result.getName(), is("chronicleName"));
		assertThat(result.getBeginDate(), is(new SimpleDateFormat("MM/dd/yyyy").parse("09/11/2001")));
	}
}

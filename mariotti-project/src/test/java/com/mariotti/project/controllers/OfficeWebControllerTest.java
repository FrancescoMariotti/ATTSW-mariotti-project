package com.mariotti.project.controllers;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mariotti.project.models.Office;
import com.mariotti.project.models.OfficeDTO;
import com.mariotti.project.services.OfficeService;

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OfficeWebController.class)
public class OfficeWebControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private OfficeService officeService;

	@Test
	public void testStatus200() throws Exception {
		mvc.perform(get("/")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testReturnHomeView() throws Exception {
		ModelAndViewAssert.assertViewName(mvc.perform(get("/")).andReturn().getModelAndView(), "index");
	}

	@Test
	public void testHomeViewShowsOffices() throws Exception {
		List<Office> offices = asList(new Office(1L, "test", new ArrayList<>()));
		when(officeService.getAllOffices()).thenReturn(offices);
		mvc.perform(get("/")).andExpect(view().name("index")).andExpect(model().attribute("offices", offices))
				.andExpect(model().attribute("message", ""));
	}

	@Test
	public void testHomeViewWithNoOfficeShowsMessage() throws Exception {
		when(officeService.getAllOffices()).thenReturn(Collections.emptyList());
		mvc.perform(get("/")).andExpect(view().name("index"))
				.andExpect(model().attribute("offices", Collections.emptyList()))
				.andExpect(model().attribute("message", "No office found"));
	}

	@Test
	public void testEditOfficeWhenItIsNotFound() throws Exception {
		when(officeService.getOfficeById(1L)).thenReturn(null);
		mvc.perform(get("/edit_office/1")).andExpect(view().name("edit_office"))
				.andExpect(model().attribute("office", nullValue()))
				.andExpect(model().attribute("message", "No office found with id: 1"));
	}

	@Test
	public void testEditOfficeWhenItIsFound() throws Exception {
		Office office = new Office(1L, "test", new ArrayList<>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		mvc.perform(get("/edit_office/1")).andExpect(view().name("edit_office"))
				.andExpect(model().attribute("office", office)).andExpect(model().attribute("message", ""));
	}

	@Test
	public void testEditNewOffice() throws Exception {
		mvc.perform(get("/new_office")).andExpect(view().name("edit_office"))
				.andExpect(model().attribute("office", new Office(new ArrayList<>())))
				.andExpect(model().attribute("message", ""));
		verifyZeroInteractions(officeService);
	}

	@Test
	public void testPostOfficeWithIdShouldUpdateExistingOffice() throws Exception {
		OfficeDTO officeDTO = new OfficeDTO(1L, "office", new ArrayList<>());
		Office office = officeMapping(officeDTO);
		when(officeService.getOfficeById(1L)).thenReturn(office);
		mvc.perform(MockMvcRequestBuilderUtils.postForm("/save_office", officeDTO))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
		verify(officeService).updateOfficeById(1L, office);
	}

	@Test
	public void testPostOfficeWithNoIdShouldAddNewOffice() throws Exception {
		OfficeDTO officeDTO = new OfficeDTO(null, "office", new ArrayList<>());
		Office office = officeMapping(officeDTO);
		when(officeService.getOfficeById(1L)).thenReturn(office);
		mvc.perform(MockMvcRequestBuilderUtils.postForm("/save_office", officeDTO))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
		verify(officeService).insertNewOffice(office);
	}

	@Test
	public void testDeleteOfficeShouldRemoveOffice() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		mvc.perform(delete("/delete_office/1")).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
		verify(officeService).deleteOfficeById(office.getId());
	}

	private Office officeMapping(OfficeDTO officeDTO) {
		Office office = new Office();
		office.setId(officeDTO.getId());
		office.setName(officeDTO.getName());
		office.setEmployees(officeDTO.getEmployees());
		return office;
	}

}

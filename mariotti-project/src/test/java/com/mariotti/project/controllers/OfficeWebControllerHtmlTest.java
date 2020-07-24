package com.mariotti.project.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static java.util.Arrays.asList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
import com.mariotti.project.services.OfficeService;

import java.util.ArrayList;
import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OfficeWebController.class)
public class OfficeWebControllerHtmlTest {

	@MockBean
	private OfficeService officeService;

	@Autowired
	private WebClient webClient;

	@Test
	public void testHomePageTitle() throws Exception {
		HtmlPage homePage = webClient.getPage("/");
		assertThat(homePage.getTitleText()).isEqualTo("Offices");
	}

	@Test
	public void testHomePageWithoutOffices() throws Exception {
		when(officeService.getAllOffices()).thenReturn(Collections.emptyList());
		HtmlPage homePage = this.webClient.getPage("/");
		assertThat(homePage.getBody().getTextContent()).contains("No office found");
	}

	@Test
	public void testHomePageWithOfficesShouldShowThemInATable() throws Exception {
		when(officeService.getAllOffices()).thenReturn(asList(new Office(1L, "office1", new ArrayList<Employee>()),
				new Office(2L, "office2", new ArrayList<Employee>())));
		HtmlPage page = this.webClient.getPage("/");
		assertThat(page.getBody().getTextContent()).doesNotContain("No office found");
		page.getHtmlElementById("office_table");
	}
}

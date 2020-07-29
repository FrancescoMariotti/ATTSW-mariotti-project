package com.mariotti.project.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static java.util.Arrays.asList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
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
		HtmlTable table = page.getHtmlElementById("office_table");
		assertThat(removeWindowsCR(table.asText()))
				.isEqualTo("Offices:\n" + "ID	Name\n" + "1	office1	View	Edit\n" + "2	office2	View	Edit");
		page.getAnchorByHref("/employees_office/1");
		page.getAnchorByHref("/employees_office/2");
		page.getAnchorByHref("/edit_office/1");
		page.getAnchorByHref("/edit_office/2");
	}

	@Test
	public void testEditInexistentOffice() throws Exception {
		when(officeService.getOfficeById(1L)).thenReturn(null);
		HtmlPage page = this.webClient.getPage("/edit_office/1");
		assertThat(page.getBody().getTextContent()).contains("No office found with id: 1");
	}

	@Test
	public void testEditExistentOffice() throws Exception {
		Office office = new Office(1L, "original name", new ArrayList<Employee>());
		when(officeService.getOfficeById(1)).thenReturn(new Office(1L, "original name", new ArrayList<Employee>()));
		HtmlPage page = this.webClient.getPage("/edit_office/1");
		final HtmlForm form = page.getFormByName("office_form");
		form.getInputByValue("original name").setValueAttribute("modified name");
		form.getButtonByName("btn_submit").click();
		verify(officeService).updateOfficeById(1L, new Office(1L, "modified name", office.getEmployees()));
	}

	@Test
	public void testEditNewOffice() throws Exception {
		HtmlPage page = this.webClient.getPage("/new_office");
		final HtmlForm form = page.getFormByName("office_form");
		form.getInputByName("name").setValueAttribute("new name");
		form.getButtonByName("btn_submit").click();
		verify(officeService).insertNewOffice(new Office(null, "new name", new ArrayList<Employee>()));
	}

	@Test
	public void testHomePageShouldHaveALinkForAddingNewOffice() throws Exception {
		HtmlPage homePage = this.webClient.getPage("/");
		assertThat(homePage.getAnchorByText("New office").getHrefAttribute()).isEqualTo("/new_office");
	}

	private String removeWindowsCR(String s) {
		return s.replaceAll("\r", "");
	}
}

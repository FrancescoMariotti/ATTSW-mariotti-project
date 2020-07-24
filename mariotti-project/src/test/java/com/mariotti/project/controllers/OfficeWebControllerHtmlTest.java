package com.mariotti.project.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mariotti.project.services.OfficeService;

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
}

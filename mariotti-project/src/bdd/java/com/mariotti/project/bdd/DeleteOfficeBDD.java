package com.mariotti.project.bdd;

import org.json.JSONException;
import org.junit.Test;

import com.mariotti.project.bdd.steps.GivenTheHomePageWithAnOffice;
import com.mariotti.project.bdd.steps.ThenTheDeletedOfficeIsNotPresent;
import com.mariotti.project.bdd.steps.WhenIDeleteThatOffice;
import com.tngtech.jgiven.junit.ScenarioTest;

public class DeleteOfficeBDD extends
		ScenarioTest<GivenTheHomePageWithAnOffice, WhenIDeleteThatOffice, ThenTheDeletedOfficeIsNotPresent> {

	@Test
	public void the_existing_office_is_deleted() throws JSONException {
		given().theHomePageWithAnOffice();
		when().iDeleteThatOffice();
		then().theDeletedOfficeIsNotPresent();
	}
}

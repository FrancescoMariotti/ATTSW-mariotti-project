package com.mariotti.project.bdd;

import org.json.JSONException;
import org.junit.Test;

import com.mariotti.project.bdd.steps.GivenTheEmployeesOfficePageWithAnEmployee;
import com.mariotti.project.bdd.steps.ThenTheDeletedEmployeeIsNotPresent;
import com.mariotti.project.bdd.steps.WhenIDeleteThatEmployee;
import com.tngtech.jgiven.junit.ScenarioTest;

public class DeleteEmployeeBDD extends
		ScenarioTest<GivenTheEmployeesOfficePageWithAnEmployee, WhenIDeleteThatEmployee, ThenTheDeletedEmployeeIsNotPresent> {

	@Test
	public void the_existing_employee_is_deleted() throws JSONException {
		given().theEmployeesOfficePageWithAnEmployee();
		when().iDeleteThatEmployee();
		then().theDeletedEmployeeIsNotPresent();
	}
}

package com.mariotti.project.bdd;

import org.json.JSONException;
import org.junit.Test;

import com.mariotti.project.bdd.steps.GivenTheEmployeesOfficePageWithAnEmployee;
import com.mariotti.project.bdd.steps.ThenTheModifiedEmployeeIsShown;
import com.mariotti.project.bdd.steps.WhenIModifyThatEmployee;
import com.tngtech.jgiven.junit.ScenarioTest;

public class ModifyEmployeeBDD  extends ScenarioTest<GivenTheEmployeesOfficePageWithAnEmployee, WhenIModifyThatEmployee, ThenTheModifiedEmployeeIsShown> {

    @Test
    public void the_existing_employee_is_modified() throws JSONException {
        given().theEmployeesOfficePageWithAnEmployee();
        when().iModifyThatEmployee();
        then().theModifiedEmployeeIsShown();
    }
}


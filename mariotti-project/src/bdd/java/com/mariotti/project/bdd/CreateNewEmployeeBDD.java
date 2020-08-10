package com.mariotti.project.bdd;

import org.junit.Test;

import com.mariotti.project.bdd.steps.GivenTheEmployeesOfficePage;
import com.mariotti.project.bdd.steps.ThenTheNewEmployeeIsShown;
import com.mariotti.project.bdd.steps.WhenICreateNewEmployee;
import com.tngtech.jgiven.junit.ScenarioTest;

public class CreateNewEmployeeBDD  extends ScenarioTest<GivenTheEmployeesOfficePage, WhenICreateNewEmployee, ThenTheNewEmployeeIsShown> {

    @Test
    public void a_new_employee_is_created() {
        given().theEmployeesOfficePage();
        when().iCreateNewEmployee();
        then().theNewEmployeeIsShown();
    }
}

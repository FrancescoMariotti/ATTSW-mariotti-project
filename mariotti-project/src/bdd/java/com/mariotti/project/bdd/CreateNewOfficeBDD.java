package com.mariotti.project.bdd;

import org.junit.Test;

import com.mariotti.project.bdd.steps.GivenTheHomePage;
import com.mariotti.project.bdd.steps.ThenTheNewOfficeIsShown;
import com.mariotti.project.bdd.steps.WhenICreateNewOffice;
import com.tngtech.jgiven.junit.ScenarioTest;

public class CreateNewOfficeBDD  extends ScenarioTest<GivenTheHomePage, WhenICreateNewOffice, ThenTheNewOfficeIsShown> {

    @Test
    public void a_new_office_is_created() {
        given().theHomePage();
        when().iCreateNewOffice();
        then().theNewOfficeIsShown();
    }
}

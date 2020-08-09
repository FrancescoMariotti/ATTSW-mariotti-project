package com.mariotti.project.bdd;

import org.junit.Test;
import com.tngtech.jgiven.junit.ScenarioTest;

public class CreateNewOfficeBDD  extends ScenarioTest<GivenTheHomePage, WhenICreateNewOffice, ThenTheNewOfficeIsShown> {

    @Test
    public void something_should_happen() {
        given().theHomePage();
        when().iCreateNewOffice();
        then().theNewOfficeIsShown();
    }
}

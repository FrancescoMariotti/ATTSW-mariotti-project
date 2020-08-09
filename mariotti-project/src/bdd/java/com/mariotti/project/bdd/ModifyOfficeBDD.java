package com.mariotti.project.bdd;

import org.json.JSONException;
import org.junit.Test;

import com.mariotti.project.bdd.steps.GivenTheHomePageWithAnOffice;
import com.mariotti.project.bdd.steps.ThenTheModifiedOfficeIsShown;
import com.mariotti.project.bdd.steps.WhenIModifyThatOffice;
import com.tngtech.jgiven.junit.ScenarioTest;

public class ModifyOfficeBDD  extends ScenarioTest<GivenTheHomePageWithAnOffice, WhenIModifyThatOffice, ThenTheModifiedOfficeIsShown> {

    @Test
    public void the_existing_office_is_modified() throws JSONException {
        given().theHomePageWithAnOffice();
        when().iModifyThatOffice();
        then().theModifiedOfficeIsShown();
    }
}


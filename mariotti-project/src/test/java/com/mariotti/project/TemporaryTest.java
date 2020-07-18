package com.mariotti.project;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

/**
 * Temporary test just to make sure jacoco.xml is generated and a report can
 * be sent to Coveralls.
 * 
 */
public class TemporaryTest {
	
	@Test
	public void test() {
		assertNotNull(new Office(1L, "office", new ArrayList<Employee>()));
	}

}

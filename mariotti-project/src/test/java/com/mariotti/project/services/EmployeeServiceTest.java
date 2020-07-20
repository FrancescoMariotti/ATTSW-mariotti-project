package com.mariotti.project.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mariotti.project.repositories.EmployeeRepository;
import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Test
	public void test_getAllEmployees() {
		Employee firstEmployee = new Employee(1L, "first", 1000, new Office(1L, "office1", new ArrayList<Employee>()));
		Employee secondEmployee = new Employee(2L, "second", 1000, new Office(2L, "office2", new ArrayList<Employee>()));
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(new Employee[] { firstEmployee, secondEmployee }));
		assertThat(employeeService.getAllEmployees()).containsExactly(firstEmployee, secondEmployee);
	}
}

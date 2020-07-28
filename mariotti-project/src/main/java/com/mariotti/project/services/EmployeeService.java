package com.mariotti.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

@Service
public class EmployeeService {

	private static final String TEMP = "Temporary implementation";
	
	public List<Employee> getEmployeesByOffice(Office office) {
		throw new UnsupportedOperationException(TEMP);
	}

	public Employee getEmployeeById(long id) {
		throw new UnsupportedOperationException(TEMP);
	}

	public void updateEmployeeById(long id, Employee employee) {
		throw new UnsupportedOperationException(TEMP);
	}

}

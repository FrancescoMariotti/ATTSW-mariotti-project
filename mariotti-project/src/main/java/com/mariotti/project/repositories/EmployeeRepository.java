package com.mariotti.project.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mariotti.project.models.Employee;

@Repository
public class EmployeeRepository {

	private static final String TEMP = "Temporary implementation";

	public List<Employee> findAll() {
		throw new UnsupportedOperationException(TEMP);
	}

}

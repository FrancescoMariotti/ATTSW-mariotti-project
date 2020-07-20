package com.mariotti.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

@Repository
public class EmployeeRepository {

	private static final String TEMP = "Temporary implementation";

	public List<Employee> findAll() {
		throw new UnsupportedOperationException(TEMP);
	}

	public List<Employee> findByOffice(Office office) {
		throw new UnsupportedOperationException(TEMP);
	}

	public Optional<Employee> findById(long id) {
		throw new UnsupportedOperationException(TEMP);
	}

	public Employee save(Employee employee) {
		throw new UnsupportedOperationException(TEMP);
	}

}

package com.mariotti.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mariotti.project.models.Employee;
import com.mariotti.project.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

}

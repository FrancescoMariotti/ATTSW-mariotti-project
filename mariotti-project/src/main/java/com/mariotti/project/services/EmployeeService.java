package com.mariotti.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
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

	public List<Employee> getEmployeesByOffice(Office office) {
		return employeeRepository.findByOffice(office);
	}

	public Employee getEmployeeById(long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	public Employee insertNewEmployee(Employee employee) {
		employee.setId(null);
		return employeeRepository.save(employee);
	}

	public Employee updateEmployeeById(long id, Employee replacement) {
		replacement.setId(id);
		return employeeRepository.save(replacement);
	}
	
	public void deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
	}

}

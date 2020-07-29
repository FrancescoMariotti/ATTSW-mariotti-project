package com.mariotti.project.services;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

@Service
public class EmployeeService {
	private Map<Long, Employee> employees = new LinkedHashMap<>();

	public EmployeeService() {
	}

	public List<Employee> getAllEmployees() {
		return new LinkedList<>(employees.values());
	}

	public Employee getEmployeeById(long i) {
		return employees.get(i);
	}

	public List<Employee> getEmployeesByOffice(Office office) {
		List<Employee> employeesByOffice = new LinkedList<>();
		for (long i = 1L; i <= employees.size(); i++) {
			Employee element = employees.get(i);
			if (element.getOffice().getId()==office.getId()) {
				employeesByOffice.add(element);
			}
		}
		return employeesByOffice;
	}

	public Employee insertNewEmployee(Employee employee) {
		employee.setId(employees.size() + 1L);
		employees.put(employee.getId(), employee);
		return employee;
	}

	public Employee updateEmployeeById(long id, Employee replacement) {
		replacement.setId(id);
		employees.put(replacement.getId(), replacement);
		return replacement;
	}
}

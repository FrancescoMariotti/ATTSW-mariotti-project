package com.mariotti.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
import com.mariotti.project.repositories.EmployeeRepository;
import com.mariotti.project.repositories.OfficeRepository;

@Service
public class OfficeService {

	private OfficeRepository officeRepository;

	private EmployeeRepository employeeRepository;

	public OfficeService(OfficeRepository officeRepository, EmployeeRepository employeeRepository) {
		this.officeRepository = officeRepository;
		this.employeeRepository = employeeRepository;
	}

	public List<Office> getAllOffices() {
		return officeRepository.findAll();
	}

	public Office getOfficeById(long id) {
		return officeRepository.findById(id).orElse(null);
	}

	public Office insertNewOffice(Office office) {
		office.setId(null);
		return officeRepository.save(office);
	}

	public Office updateOfficeById(long id, Office replacement) {
		replacement.setId(id);
		return officeRepository.save(replacement);
	}

	public void deleteOfficeById(long id) {
		Office officeToDelete = officeRepository.findById(id).orElse(null);
		List<Employee> employeesToDelete = employeeRepository.findByOffice(officeToDelete);
		for (Employee employee : employeesToDelete) {
			employeeRepository.deleteById(employee.getId());
		}
		officeRepository.deleteById(id);
	}

}

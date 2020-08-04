package com.mariotti.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findAll();
	
	Employee findByOffice(Office office);

	List<Employee> findAllByOffice(Office office);
	
	Employee findByName(String name);

}

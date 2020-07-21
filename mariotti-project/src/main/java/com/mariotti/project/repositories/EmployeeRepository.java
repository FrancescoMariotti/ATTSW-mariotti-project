package com.mariotti.project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	Employee findByOffice(Office office);

	Iterable<Employee> findAllByOffice(Office office);

}

package com.mariotti.project;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
import com.mariotti.project.repositories.EmployeeRepository;
import com.mariotti.project.repositories.OfficeRepository;
import com.mariotti.project.services.EmployeeService;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(EmployeeService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeServiceRepositoryIT {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private OfficeRepository officeRepository;

	@Test
	public void testEmployeeServiceShallInsertIntoEmployeeRepository() {
		Office office = officeRepository.save(new Office(null, "office", new ArrayList<>()));
		Employee employee = employeeService.insertNewEmployee(new Employee(null, "test", 1000, office));
		assertThat(employeeRepository.findById(employee.getId()).isPresent()).isTrue();
	}

	@Test
	public void testEmployeeServiceShallUpdateEmployeeRepository() {
		Office office = officeRepository.save(new Office(null, "office", new ArrayList<>()));
		Employee saved = employeeRepository.save(new Employee(null, "old", 1000, office));
		Employee modified = employeeService.updateEmployeeById(saved.getId(),
				new Employee(saved.getId(), "modified", 2000, office));
		assertThat(employeeRepository.findById(saved.getId()).get()).isEqualTo(modified);
	}
}

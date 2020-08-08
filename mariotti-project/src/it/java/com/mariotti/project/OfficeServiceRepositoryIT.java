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
import com.mariotti.project.services.OfficeService;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(OfficeService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OfficeServiceRepositoryIT {
	
	@Autowired
	private OfficeService officeService;
	@Autowired
	private OfficeRepository officeRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testOfficeServiceShallInsertIntoOfficeRepository() {
		Office office = officeService.insertNewOffice(new Office(null, "test", new ArrayList<>()));
		assertThat(officeRepository.findById(office.getId())).isPresent();
	}
	
	@Test
	public void testOfficeServiceShallUpdateOfficeRepository() {
		Office saved = officeRepository.save(new Office(null, "old", new ArrayList<>()));
		Office modified = officeService.updateOfficeById(saved.getId(),
				new Office(saved.getId(), "modified", saved.getEmployees()));
		assertThat(officeRepository.findById(saved.getId()).get()).isEqualTo(modified);
	}
	
	@Test
	public void testOfficeServiceShallDeleteFromOfficeRepository() {
		Office office = officeRepository.save(new Office(null, "office", new ArrayList<>()));
		Employee employee = employeeRepository.save(new Employee(null, "employee", 1000, office));
		officeService.deleteOfficeById(office.getId());
		assertThat(officeRepository.findById(office.getId())).isNotPresent();
		assertThat(employeeRepository.findById(employee.getId())).isNotPresent();
	}
}

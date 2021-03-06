package com.mariotti.project.repositories;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

@DataJpaTest
@RunWith(SpringRunner.class)
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private TestEntityManager manager;

	@Test
	public void testFindAllEmployees() {
		Office office = new Office(null, "office", new ArrayList<>());
		manager.persistFlushFind(office);
		Employee employee = new Employee(null, "employee", 1000, office);
		Employee saved = manager.persistFlushFind(employee);
		List<Employee> found = repository.findAll();
		assertThat(found).containsExactly(saved);
	}

	@Test
	public void testFindOneByOffice() {
		Office office1 = new Office(null, "office1", new ArrayList<>());
		Office office2 = new Office(null, "office2", new ArrayList<>());
		manager.persistFlushFind(office1);
		manager.persistFlushFind(office2);
		Employee employee1 = new Employee(null, "employee1", 1000, office1);
		Employee employee2 = new Employee(null, "employee2", 2000, office2);
		Employee saved = manager.persistFlushFind(employee1);
		manager.persistFlushFind(employee2);
		Employee found = repository.findOneByOffice(office1);
		assertThat(found).isEqualTo(saved);
	}

	@Test
	public void testFindByOffice() {
		Office office1 = new Office(null, "office1", new ArrayList<>());
		Office office2 = new Office(null, "office2", new ArrayList<>());
		manager.persistFlushFind(office1);
		manager.persistFlushFind(office2);
		Employee employee1 = new Employee(null, "employee1", 1000, office1);
		Employee employee2 = new Employee(null, "employee2", 2000, office2);
		Employee saved = manager.persistFlushFind(employee1);
		manager.persistFlushFind(employee2);
		List<Employee> found = repository.findByOffice(office1);
		assertThat(found).containsExactly(saved);
	}
	
	@Test
	public void testFindByName() {
		Office office = new Office(null, "office", new ArrayList<>());
		manager.persistFlushFind(office);
		Employee employee = new Employee(null, "employee", 1000, office);
		Employee saved = manager.persistFlushFind(employee);
		Employee found = repository.findByName(employee.getName());
		assertThat(found).isEqualTo(saved);
	}
}

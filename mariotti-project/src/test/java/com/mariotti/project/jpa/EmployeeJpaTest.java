package com.mariotti.project.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

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
public class EmployeeJpaTest {
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testJpaMapping() {
		Office office = entityManager.persistFlushFind(new Office(null, "office", new ArrayList<Employee>()));
		Employee saved = entityManager.persistFlushFind(new Employee(null, "employee", 1000, office));
		assertThat(saved.getName()).isEqualTo("employee");
		assertThat(saved.getSalary()).isEqualTo(1000);
		assertThat(saved.getOffice()).isEqualTo(office);
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getId()).isPositive();
	}
}

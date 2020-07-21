package com.mariotti.project.repositories;

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
public class OfficeRepositoryTest {

	@Autowired
	private OfficeRepository repository;
	
	@Autowired
	private TestEntityManager manager;

	@Test
	public void testSaveOffice() {
		Office office = new Office(null, "office", new ArrayList<Employee>());
		Office saved = repository.save(office);
		Iterable<Office> found = repository.findAll();
		assertThat(found).containsExactly(saved);
	}
	
	@Test
	public void testFindAllOffices() {
		Office office = new Office(null, "office", new ArrayList<Employee>());
		Office saved = manager.persistFlushFind(office);
		Iterable<Office> found = repository.findAll();
		assertThat(found).containsExactly(saved);
	}
	
	@Test
	public void testFindByName() {
		Office office = new Office(null, "office", new ArrayList<Employee>());
		Office saved = manager.persistFlushFind(office);
		Office found = repository.findByName("office");
		assertThat(found).isEqualTo(saved);
	}
	
}

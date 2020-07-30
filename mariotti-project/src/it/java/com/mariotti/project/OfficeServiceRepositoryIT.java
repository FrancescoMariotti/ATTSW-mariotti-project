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

import com.mariotti.project.models.Office;
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

	@Test
	public void testOfficeServiceShallInsertIntoOfficeRepository() {
		Office office = officeService.insertNewOffice(new Office(null, "test", new ArrayList<>()));
		officeRepository.findById(office.getId()).isPresent();
	}
	
	@Test
	public void testOfficeServiceShallUpdateOfficeRepository() {
		Office saved = officeRepository.save(new Office(null, "old", new ArrayList<>()));
		Office modified = officeService.updateOfficeById(saved.getId(),
				new Office(saved.getId(), "modified", saved.getEmployees()));
		assertThat(officeRepository.findById(saved.getId()).get()).isEqualTo(modified);
	}
}

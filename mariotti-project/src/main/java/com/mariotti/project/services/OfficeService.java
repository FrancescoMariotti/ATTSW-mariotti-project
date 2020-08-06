package com.mariotti.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariotti.project.models.Office;
import com.mariotti.project.repositories.OfficeRepository;

@Service
public class OfficeService {

	@Autowired
	private OfficeRepository officeRepository;

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
		officeRepository.deleteById(id);
	}

}

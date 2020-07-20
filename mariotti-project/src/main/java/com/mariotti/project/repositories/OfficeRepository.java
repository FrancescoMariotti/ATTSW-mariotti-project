package com.mariotti.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mariotti.project.models.Office;

@Repository
public class OfficeRepository {

	private static final String TEMP = "Temporary implementation";

	public List<Office> findAll() {
		throw new UnsupportedOperationException(TEMP);
	}

	public Optional<Office> findById(long id) {
		throw new UnsupportedOperationException(TEMP);
	}

	public Office save(Office office) {
		throw new UnsupportedOperationException(TEMP);
	}

}

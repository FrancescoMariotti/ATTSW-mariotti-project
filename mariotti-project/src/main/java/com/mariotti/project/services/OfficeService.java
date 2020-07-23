package com.mariotti.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mariotti.project.models.Office;

@Service
public class OfficeService {

	private static final String TEMP = "Temporary implementation";

	public List<Office> getAllOffices() {
		throw new UnsupportedOperationException(TEMP);
	}

	public Office getOfficeById(long id) {
		throw new UnsupportedOperationException(TEMP);
	}

}

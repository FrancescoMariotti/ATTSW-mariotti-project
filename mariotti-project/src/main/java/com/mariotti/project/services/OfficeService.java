package com.mariotti.project.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

@Service
public class OfficeService {

	private Map<Long, Office> offices = new LinkedHashMap<>();
	private ArrayList<Employee> employees;

	public OfficeService() {
		this.employees = new ArrayList<Employee>();
		offices.put(1L, new Office(1L, "shop office", this.employees));
		offices.put(2L, new Office(2L, "production office", this.employees));
	}

	public List<Office> getAllOffices() {
		return new LinkedList<>(offices.values());
	}

	public Office getOfficeById(long id) {
		return offices.get(id);
	}

	public Office updateOfficeById(long id, Office office) {
		office.setId(id);
		offices.put(office.getId(), office);
		return office;
	}

	public Office insertNewOffice(Office office) {
		office.setId(offices.size() + 1L);
		offices.put(office.getId(), office);
		return office;
	}

}

package com.mariotti.project.models;

import java.util.Collection;

public class OfficeDTO {
	private Long id;
	private String name;
	private Collection<Employee> employees;

	public OfficeDTO(Long id, String name, Collection<Employee> employees) {
		this.id = id;
		this.name = name;
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}

}

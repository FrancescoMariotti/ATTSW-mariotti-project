package com.mariotti.project.models;

public class EmployeeDTO {

	private Long id;
	private String name;
	private long salary;
	private Office office;

	public EmployeeDTO(Long id, String name, long salary, Office office) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.office = office;
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

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

}

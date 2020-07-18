package com.mariotti.project.models;

import java.util.Collection;
import java.util.Objects;

public class Office {
	private Long id;
	private String name;
	private Collection<Employee> employees;
	
	public Office(Long id, String name, Collection<Employee> employees) {
		this.id=id;
		this.name=name;
		this.employees=employees;
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

	@Override
	public String toString() {
		return "Office [id=" + id + ", name=" + name + ", employees=" + employees + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employees, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Office other = (Office) obj;
		return Objects.equals(employees, other.employees) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}
	
}

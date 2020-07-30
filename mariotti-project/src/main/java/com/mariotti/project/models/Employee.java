package com.mariotti.project.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Employee")
@Table(name="employee")
public class Employee {
	
	@Id @GeneratedValue
	private Long id;
	private String name;
	private long salary;
	@ManyToOne(fetch = FetchType.LAZY)
	private Office office;
	
	public Employee(Long id, String name, long salary, Office office) {
		this.id=id;
		this.name=name;
		this.salary=salary;
		this.office=office;
	}

	public Employee() {
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", office=" + office + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, office, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(office, other.office)
				&& salary == other.salary;
	}
	
}

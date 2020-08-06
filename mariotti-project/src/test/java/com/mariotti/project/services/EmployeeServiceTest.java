package com.mariotti.project.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mariotti.project.repositories.EmployeeRepository;
import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;
	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	public void testGetAllEmployees() {
		Employee firstEmployee = new Employee(1L, "first", 1000, new Office(1L, "office1", new ArrayList<>()));
		Employee secondEmployee = new Employee(2L, "second", 1000, new Office(2L, "office2", new ArrayList<>()));
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(new Employee[] { firstEmployee, secondEmployee }));
		assertThat(employeeService.getAllEmployees()).containsExactly(firstEmployee, secondEmployee);
	}

	@Test
	public void testGetEmployeesByOffice() {
		Office office1 = new Office(1L, "office1", new ArrayList<>());
		Employee employee1 = new Employee(1L, "first", 1000, office1);
		Employee employee2 = new Employee(2L, "second", 1000, office1);
		when(employeeRepository.findByOffice(office1))
				.thenReturn(Arrays.asList(new Employee[] { employee1, employee2 }));
		assertThat(employeeService.getEmployeesByOffice(office1)).containsExactly(employee1, employee2);
	}

	@Test
	public void testGetEmployeeById_notFound() {
		when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThat(employeeService.getEmployeeById(1)).isNull();
	}

	@Test
	public void test_getEmployeeById_Found() {
		Employee employee = new Employee(1L, "employee", 1000, new Office(1L, "office", new ArrayList<>()));
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		assertThat(employeeService.getEmployeeById(1)).isSameAs(employee);
	}

	@Test
	public void testInsertNewEmployee() {
		Employee toSave = spy(new Employee(99L, "", 0, null));
		Employee saved = new Employee(1L, "saved", 1000, new Office(1L, "office", new ArrayList<>()));
		when(employeeRepository.save(any(Employee.class))).thenReturn(saved);
		Employee result = employeeService.insertNewEmployee(toSave);
		assertThat(result).isSameAs(saved);
		InOrder inOrder = inOrder(toSave, employeeRepository);
		inOrder.verify(toSave).setId(null);
		inOrder.verify(employeeRepository).save(toSave);
	}

	@Test
	public void testUpdateEmployeeById() {
		Employee replacement = spy(new Employee(null, "employee", 0, null));
		Employee replaced = new Employee(1L, "saved", 1000, new Office(1L, "office", new ArrayList<>()));
		when(employeeRepository.save(any(Employee.class))).thenReturn(replaced);
		Employee result = employeeService.updateEmployeeById(1L, replacement);
		assertThat(result).isSameAs(replaced);
		InOrder inOrder = inOrder(replacement, employeeRepository);
		inOrder.verify(replacement).setId(1L);
		inOrder.verify(employeeRepository).save(replacement);
	}

	@Test
	public void testDeleteEmployeeById() {
		Employee employee = new Employee(1L, "employee", 1000, new Office(1L, "office", new ArrayList<>()));
		employeeService.deleteEmployeeById(employee.getId());
		verify(employeeRepository,times(1)).deleteById(employee.getId());
	}
}

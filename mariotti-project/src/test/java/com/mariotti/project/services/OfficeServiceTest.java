package com.mariotti.project.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
import com.mariotti.project.repositories.EmployeeRepository;
import com.mariotti.project.repositories.OfficeRepository;

@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceTest {

	@InjectMocks
	private OfficeService officeService;
	@Mock
	private OfficeRepository officeRepository;
	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	public void testGetAllOffices() {
		Office firstOffice = new Office(1L, "office1", new ArrayList<>());
		Office secondOffice = new Office(2L, "office2", new ArrayList<>());
		when(officeRepository.findAll()).thenReturn(Arrays.asList(new Office[] { firstOffice, secondOffice }));
		assertThat(officeService.getAllOffices()).containsExactly(firstOffice, secondOffice);
	}

	@Test
	public void testGetOfficeById_notFound() {
		when(officeRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThat(officeService.getOfficeById(1)).isNull();
	}

	@Test
	public void testGetOfficeById_Found() {
		Office office = new Office(1L, "office", new ArrayList<>());
		when(officeRepository.findById(1L)).thenReturn(Optional.of(office));
		assertThat(officeService.getOfficeById(1)).isSameAs(office);
	}

	@Test
	public void testInsertNewOffice() {
		Office toSave = spy(new Office(99L, "", null));
		Office saved = new Office(1L, "office", new ArrayList<>());
		when(officeRepository.save(any(Office.class))).thenReturn(saved);
		Office result = officeService.insertNewOffice(toSave);
		assertThat(result).isSameAs(saved);
		InOrder inOrder = inOrder(toSave, officeRepository);
		inOrder.verify(toSave).setId(null);
		inOrder.verify(officeRepository).save(toSave);
	}

	@Test
	public void testUpdateOfficeById() {
		Office replacement = spy(new Office(99L, "", null));
		Office replaced = new Office(1L, "office", new ArrayList<>());
		when(officeRepository.save(any(Office.class))).thenReturn(replaced);
		Office result = officeService.updateOfficeById(1L, replacement);
		assertThat(result).isSameAs(replaced);
		InOrder inOrder = inOrder(replacement, officeRepository);
		inOrder.verify(replacement).setId(1L);
		inOrder.verify(officeRepository).save(replacement);
	}

	@Test
	public void testDeleteOfficeByIdFound() {
		Office office = new Office(1L, "office", new ArrayList<>());
		when(officeRepository.findById(1L)).thenReturn(Optional.of(office));
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1L, "employee", 1000, office));
		when(employeeRepository.findByOffice(office)).thenReturn(employees);
		officeService.deleteOfficeById(office.getId());
		for (Employee employee : employees) {
			verify(employeeRepository, times(1)).deleteById(employee.getId());
		}
		verify(officeRepository, times(1)).deleteById(office.getId());
	}

}

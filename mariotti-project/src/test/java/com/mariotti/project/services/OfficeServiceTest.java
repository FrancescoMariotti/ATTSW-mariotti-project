package com.mariotti.project.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
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
import com.mariotti.project.repositories.OfficeRepository;

@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceTest {

	@InjectMocks
	private OfficeService officeService;
	@Mock
	private OfficeRepository officeRepository;
	
	@Test
	public void test_getAllOffices() {
		Office firstOffice = new Office(1L, "office1", new ArrayList<Employee>());
		Office secondOffice = new Office(2L, "office2", new ArrayList<Employee>());
		when(officeRepository.findAll()).thenReturn(Arrays.asList(new Office[] { firstOffice, secondOffice }));
		assertThat(officeService.getAllOffices()).containsExactly(firstOffice, secondOffice);
	}
	
	@Test
	public void test_getOfficeById_notFound() {
		when(officeRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThat(officeService.getOfficeById(1)).isNull();
	}

	@Test
	public void test_getOfficeById_Found() {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeRepository.findById(1L)).thenReturn(Optional.of(office));
		assertThat(officeService.getOfficeById(1)).isSameAs(office);
	}
	
	@Test
	public void test_insertNewOffice() {
		Office toSave = spy(new Office(99L, "", null));
		Office saved = new Office(1L, "office", new ArrayList<Employee>());
		when(officeRepository.save(any(Office.class))).thenReturn(saved);
		Office result = officeService.insertNewOffice(toSave);
		assertThat(result).isSameAs(saved);
		InOrder inOrder = inOrder(toSave, officeRepository);
		inOrder.verify(toSave).setId(null);
		inOrder.verify(officeRepository).save(toSave);
	}
	
	@Test
	public void test_updateOfficeById() {
		Office replacement = spy(new Office(99L, "", null));
		Office replaced = new Office(1L, "office", new ArrayList<Employee>());
		when(officeRepository.save(any(Office.class))).thenReturn(replaced);
		Office result = officeService.updateOfficeById(1L, replacement);
		assertThat(result).isSameAs(replaced);
		InOrder inOrder = inOrder(replacement, officeRepository);
		inOrder.verify(replacement).setId(1L);
		inOrder.verify(officeRepository).save(replacement);
	}
	
}

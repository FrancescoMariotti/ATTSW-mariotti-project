package com.mariotti.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mariotti.project.models.Office;

public interface OfficeRepository extends CrudRepository<Office, Long> {

	List<Office> findAll();
	
	Office findByName(String name);

}

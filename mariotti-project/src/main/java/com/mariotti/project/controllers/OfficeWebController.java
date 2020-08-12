package com.mariotti.project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mariotti.project.models.Office;
import com.mariotti.project.models.OfficeDTO;
import com.mariotti.project.services.OfficeService;

@Controller
public class OfficeWebController {

	private static final String OFFICE_ATTRIBUTE = "office";

	private static final String MESSAGE = "message";

	@Autowired
	private OfficeService officeService;

	@GetMapping("/")
	public String index(Model model) {
		List<Office> offices = officeService.getAllOffices();
		model.addAttribute("offices", offices);
		model.addAttribute(MESSAGE, offices.isEmpty() ? "No office found" : "");
		return "index";
	}

	@GetMapping("/edit_office/{id}")
	public String editOffice(@PathVariable long id, Model model) {
		Office officeById = officeService.getOfficeById(id);
		model.addAttribute(OFFICE_ATTRIBUTE, officeById);
		model.addAttribute(MESSAGE, officeById == null ? "No office found with id: " + id : "");
		return "edit_office";
	}

	@GetMapping("/new_office")
	public String newOffice(Model model) {
		model.addAttribute(OFFICE_ATTRIBUTE, new Office(new ArrayList<>()));
		model.addAttribute(MESSAGE, "");
		return "edit_office";
	}

	@PostMapping("/save_office")
	public String saveOffice(OfficeDTO officeDTO) {
		Office office=officeMapping(officeDTO);
		final Long id = office.getId();
		if (id == null) {
			office.setEmployees(new ArrayList<>());
			officeService.insertNewOffice(office);
		} else {
			Office existent = officeService.getOfficeById(id);
			office.setEmployees(existent.getEmployees());
			officeService.updateOfficeById(id, office);
		}
		return "redirect:/";
	}
	

	@RequestMapping(value = "/delete_office/{officeId}", method = { RequestMethod.GET,
			RequestMethod.DELETE })
	public String deleteEmployee(@PathVariable long officeId, Model model) {
		Office officeById = officeService.getOfficeById(officeId);
		model.addAttribute(OFFICE_ATTRIBUTE, officeById);
		officeService.deleteOfficeById(officeId);
		return "redirect:/";
	}
	
	private Office officeMapping(OfficeDTO officeDTO) {
		Office office = new Office();
		office.setId(officeDTO.getId());
		office.setName(officeDTO.getName());
		office.setEmployees(officeDTO.getEmployees());
		return office;
	}
}

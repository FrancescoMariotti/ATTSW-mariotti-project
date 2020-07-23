package com.mariotti.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mariotti.project.models.Office;
import com.mariotti.project.services.OfficeService;

@Controller
public class OfficeWebController {

	@Autowired
	private OfficeService officeService;

	@GetMapping("/")
	public String index(Model model) {
		List<Office> offices = officeService.getAllOffices();
		model.addAttribute("offices", offices);
		model.addAttribute("message", offices.isEmpty() ? "No office found" : "");
		return "index";
	}

	@GetMapping("/edit_office/{id}")
	public String editOffice(@PathVariable long id, Model model) {
		Office officeById = officeService.getOfficeById(id);
		model.addAttribute("office", officeById);
		model.addAttribute("message", officeById == null ? "No office found with id: " + id : "");
		return "edit_office";
	}

	@GetMapping("/new_office")
	public String newOffice(Model model) {
		model.addAttribute("office", new Office());
		model.addAttribute("message", "");
		return "edit_office";
	}

}

package com.mariotti.project.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mariotti.project.models.Office;
import com.mariotti.project.services.OfficeService;


@Controller
public class EmployeeWebController {

	@Autowired
	private OfficeService officeService;

	@GetMapping("/employees_office/{officeId}")
	public String index(@PathVariable long officeId, Model model) {
		Office officeById = officeService.getOfficeById(officeId);
		model.addAttribute("office", officeById);
		return "employees_office";
	}
}

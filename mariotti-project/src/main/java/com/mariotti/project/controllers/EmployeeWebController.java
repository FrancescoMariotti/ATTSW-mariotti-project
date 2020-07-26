package com.mariotti.project.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EmployeeWebController {

	@GetMapping("/employees_office/{officeId}")
	public String index(Model model) {
		return "employees_office";
	}
}

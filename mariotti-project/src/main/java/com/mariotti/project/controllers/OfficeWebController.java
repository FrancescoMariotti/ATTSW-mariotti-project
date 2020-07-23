package com.mariotti.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mariotti.project.services.OfficeService;

@Controller
public class OfficeWebController {
	
	@Autowired
	private OfficeService officeService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("offices",
				officeService.getAllOffices());
		return "index";
	}
	
}

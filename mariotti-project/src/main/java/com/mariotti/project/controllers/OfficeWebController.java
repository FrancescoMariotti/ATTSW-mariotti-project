package com.mariotti.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfficeWebController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
}

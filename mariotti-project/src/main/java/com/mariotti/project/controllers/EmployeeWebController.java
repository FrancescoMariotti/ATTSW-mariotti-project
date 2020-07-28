package com.mariotti.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
import com.mariotti.project.services.EmployeeService;
import com.mariotti.project.services.OfficeService;

@Controller
public class EmployeeWebController {

	private static final String MESSAGE = "message";

	@Autowired
	private OfficeService officeService;
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees_office/{officeId}")
	public String employeesOffice(@PathVariable long officeId, Model model) {
		Office officeById = officeService.getOfficeById(officeId);
		model.addAttribute("office", officeById);
		List<Employee> employees = employeeService.getEmployeesByOffice(officeById);
		model.addAttribute("employees", employees);
		model.addAttribute(MESSAGE, employees.isEmpty() ? "No employee found in this office" : "");
		return "employees_office";
	}

	@GetMapping("/employees_office/{officeId}/edit_employee/{employeeId}")
	public String editEmployee(@PathVariable long officeId, @PathVariable long employeeId, Model model) {
		Office officeById = officeService.getOfficeById(officeId);
		model.addAttribute("office", officeById);
		Employee employeebyId = employeeService.getEmployeeById(employeeId);
		model.addAttribute("employee", employeebyId);
		model.addAttribute(MESSAGE, employeebyId == null ? "No employee found with id: " + employeeId : "");
		return "edit_employee";
	}
}

package com.mariotti.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.EmployeeDTO;
import com.mariotti.project.models.Office;
import com.mariotti.project.services.EmployeeService;
import com.mariotti.project.services.OfficeService;

@Controller
public class EmployeeWebController {

	private static final String EMPLOYEE_ATTRIBUTE = "employee";

	private static final String OFFICE_ATTRIBUTE = "office";

	private static final String MESSAGE = "message";

	@Autowired
	private OfficeService officeService;
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees_office/{officeId}")
	public String employeesOffice(@PathVariable long officeId, Model model) {
		Office officeById = officeService.getOfficeById(officeId);
		model.addAttribute(OFFICE_ATTRIBUTE, officeById);
		List<Employee> employees = employeeService.getEmployeesByOffice(officeById);
		model.addAttribute("employees", employees);
		model.addAttribute(MESSAGE, employees.isEmpty() ? "No employee found in this office" : "");
		return "employees_office";
	}

	@GetMapping("/employees_office/{officeId}/edit_employee/{employeeId}")
	public String editEmployee(@PathVariable long officeId, @PathVariable long employeeId, Model model) {
		Office officeById = officeService.getOfficeById(officeId);
		model.addAttribute(OFFICE_ATTRIBUTE, officeById);
		Employee employeebyId = employeeService.getEmployeeById(employeeId);
		model.addAttribute(EMPLOYEE_ATTRIBUTE, employeebyId);
		model.addAttribute(MESSAGE, employeebyId == null ? "No employee found with id: " + employeeId : "");
		return "edit_employee";
	}

	@GetMapping("/employees_office/{officeId}/new_employee")
	public String newEmployee(@PathVariable long officeId, Model model) {
		Office officeById = officeService.getOfficeById(officeId);
		model.addAttribute(OFFICE_ATTRIBUTE, officeById);
		model.addAttribute(EMPLOYEE_ATTRIBUTE, new Employee(officeById));
		model.addAttribute(MESSAGE, "");
		return "edit_employee";
	}

	@PostMapping("/employees_office/{officeId}/save_employee")
	public String saveEmployee(@PathVariable long officeId, EmployeeDTO employeeDTO) {
		Office officeById = officeService.getOfficeById(officeId);
		employeeDTO.setOffice(officeById);
		Employee employee = employeeMapping(employeeDTO);
		final Long employeeId = employee.getId();
		if (employeeId == null) {
			employeeService.insertNewEmployee(employee);
		} else {
			employeeService.updateEmployeeById(employeeId, employee);
		}
		return "redirect:/employees_office/" + officeId;
	}

	@RequestMapping(value = "/employees_office/{officeId}/delete_employee/{employeeId}", method = { RequestMethod.GET,
			RequestMethod.DELETE })
	public String deleteEmployee(@PathVariable long officeId, @PathVariable long employeeId, Model model) {
		Office officeById = officeService.getOfficeById(officeId);
		model.addAttribute(OFFICE_ATTRIBUTE, officeById);
		employeeService.deleteEmployeeById(employeeId);
		return "redirect:/employees_office/" + officeId;
	}
	
	public static Employee employeeMapping(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setId(employeeDTO.getId());
		employee.setName(employeeDTO.getName());
		employee.setSalary(employeeDTO.getSalary());
		employee.setOffice(employeeDTO.getOffice());
		return employee;
	}
}

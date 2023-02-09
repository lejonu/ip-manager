package ipmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ipmanager.model.Employee;
import ipmanager.repository.EmployeeRepository;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository empRepo;
	
	@GetMapping("")
	public String displayEmployees(Model model) {
		List<Employee> employees = empRepo.findAll();
		
		model.addAttribute("employeeList" ,employees);
		
		return "employees/list-employees";
	}
}
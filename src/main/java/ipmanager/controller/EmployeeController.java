package ipmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

		model.addAttribute("employeeList", employees);

		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String Employee(Model model) {

		Employee anEmployee = new Employee();

		model.addAttribute("employee", anEmployee);

		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		empRepo.save(employee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") long id, Model model) {

		Employee employee = empRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee id"));

		empRepo.delete(employee);

		return "redirect:/employees";
	}

	@GetMapping("/edit/{id}")
	public String editEmployee(@PathVariable("id") Long id, Model model) {
		
		Employee employee = empRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee id"));

		model.addAttribute(employee);

		return "employees/edit-employee";

	}
	
	@PostMapping("/update/{id}")
	public String udpateEmployee(@PathVariable("id") Long id, Employee employee, Model model) {
		Optional<Employee> emp = empRepo.findById(id);
		
		if(emp.isPresent()) {
			employee.setEmployeeId(id);
			empRepo.save(employee);
		} else {
			throw new IllegalArgumentException("Invalid Id");
		}
		
		return "redirect:/employees";
	}

}
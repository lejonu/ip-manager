package ipmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ipmanager.model.Employee;
import ipmanager.model.Ip;
import ipmanager.repository.EmployeeRepository;
import ipmanager.service.impl.IpServiceImpl;

@Controller
public class HomeController {
	@Value("${version}")
	private String ver;
	
	@Autowired
	IpServiceImpl ipImpl;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayModel(Model model) {
		model.addAttribute("versionNumber", ver);
		
		List<Ip> ips = ipImpl.findAll();
		model.addAttribute("ipList", ips);
		
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employeeList", employees);
		
		return "main/home";
		
	}
}

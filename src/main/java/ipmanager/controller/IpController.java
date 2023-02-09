package ipmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ipmanager.model.Employee;
import ipmanager.model.Ip;
import ipmanager.repository.EmployeeRepository;
import ipmanager.service.impl.IpServiceImpl;

@Controller
@RequestMapping("/ips")
public class IpController {
	@Autowired
	private IpServiceImpl ipServiceImpl;
	
	@Autowired
	private EmployeeRepository empRepo;

	@GetMapping("")
	public String displayIps(Model model) {
		List<Ip> ips = ipServiceImpl.findAll();
		
		model.addAttribute("ipList", ips);
		
		return "ips/list-ips";
	}
	
	@GetMapping("/new")
	public String createIp(Model model) {
		
		Ip anIp = new Ip();
			
		List<Employee> employees = empRepo.findAll();
		
		model.addAttribute("ip", anIp);
		
		model.addAttribute("allEmployees", employees);

		return "ips/new-ip";
	}
	
	@PostMapping("/save")
	public String createProject(Ip ip, Model model) {

		ipServiceImpl.save(ip);

		return "redirect:/ips";
	}
}

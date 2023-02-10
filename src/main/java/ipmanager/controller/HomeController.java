package ipmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ipmanager.dto.ChartData;
import ipmanager.dto.EmployeeIp;
import ipmanager.model.Ip;
import ipmanager.repository.EmployeeRepository;
import ipmanager.repository.IpRepository;
import ipmanager.service.impl.IpServiceImpl;

@Controller
public class HomeController {
	@Value("${version}")
	private String ver;

	@Autowired
	IpRepository ipRepo;

	@Autowired
	IpServiceImpl ipImplRepo;

	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/")
	public String displayModel(Model model) throws JsonProcessingException {
		model.addAttribute("versionNumber", ver);

		List<Ip> ips = ipImplRepo.findAll();
		model.addAttribute("ipList", ips);

		List<ChartData> ipData = ipRepo.getIpCount();

		Map<String, Object> map = new HashMap<>();

		ObjectMapper objectMapper = new ObjectMapper();

		String jsonString = objectMapper.writeValueAsString(ipData);
		
		model.addAttribute("ipQueryCount", jsonString);

		List<EmployeeIp> employeesIpCount = empRepo.employeeIps();
		model.addAttribute("employeeListIpCount", employeesIpCount);

		return "main/home";

	}
}

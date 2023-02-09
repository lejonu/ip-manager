package ipmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ipmanager.model.Ip;
import ipmanager.service.impl.IpServiceImpl;

@Controller
@RequestMapping("/ips")
public class IpController {
	@Autowired
	private IpServiceImpl ipServiceImpl;

	@GetMapping("")
	public String displayIps(Model model) {
		List<Ip> ips = ipServiceImpl.findAll();
		
		model.addAttribute("ipList", ips);
		
		return "ips/list-ips";
	}
}

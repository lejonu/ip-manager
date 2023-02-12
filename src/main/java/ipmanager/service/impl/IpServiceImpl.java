package ipmanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ipmanager.model.Ip;
import ipmanager.repository.EmployeeRepository;
import ipmanager.repository.IpRepository;
import ipmanager.service.IpApiService;
import ipmanager.service.IpService;

@Service
public class IpServiceImpl implements IpService {
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private IpRepository ipRepo;

	@Autowired
	private IpApiService ipApiService;

	@Override
	public List<Ip> findAll() {
		return ipRepo.findAll();
	}

	@Override
	public Ip findById(Long id) {
		Optional<Ip> ip = ipRepo.findById(id);
		return ip.get();
	}

	@Override
	public void save(Ip ip) {

		saveWithEmployee(ip);
	}

	private void saveWithEmployee(Ip ip) {
		Ip newIp = ipApiService.consultIP(ip.getQuery());
		newIp.setEmployees(ip.getEmployees());
		newIp.setStage(ip.getStage());

		ipRepo.save(newIp);
	}

	private void updateWithEmployee(Long id, Ip ip) {
		Ip newIp = ipApiService.consultIP(ip.getQuery());
		newIp.setEmployees(ip.getEmployees());
		newIp.setStage(ip.getStage());
		newIp.setIpId(id);

		ipRepo.save(newIp);
	}

	@Override
	public void update(Long id, Ip ip) {
//		ip.setIpId(id);
		updateWithEmployee(id, ip);
	}

	@Override
	public void delete(Long id) {
		ipRepo.deleteById(id);
	}

	
	public void deleteIp(Ip ip) {
		ipRepo.delete(ip);
	}

}

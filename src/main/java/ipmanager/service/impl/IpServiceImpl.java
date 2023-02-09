package ipmanager.service.impl;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Ip ip) {

		saveWitEmployee(ip);
	}

	private void saveWitEmployee(Ip ip) {
		Ip newIp = ipApiService.consultIP(ip.getQuery());
		newIp.setEmployees(ip.getEmployees());
		
		ipRepo.save(newIp);
	}

	@Override
	public void update(Long id, Ip ip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}

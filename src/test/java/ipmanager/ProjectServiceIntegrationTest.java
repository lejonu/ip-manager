package ipmanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ipmanager.model.Employee;
import ipmanager.model.Ip;
import ipmanager.repository.EmployeeRepository;
import ipmanager.service.impl.IpServiceImpl;

@SpringBootTest
public class ProjectServiceIntegrationTest {
	@Autowired
	private IpServiceImpl ipServRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Test
	public void ifNewIpSaveApiInfo_thenSuccess() {
		
		Ip ip = new Ip();
		ip.setIpId(1L);
		ip.setQuery("23.23.23.23");
		
		ipServRepo.save(ip);
		
		assertEquals("23.23.23.23", ipServRepo.findById(1L).getQuery());
		
		assertEquals("success", ipServRepo.findById(1L).getStatus());
	}
	
	@Test
	public void ifIpUpdateWithEmployees_thenSuccess() {
		Employee newEmployee = new Employee();
		newEmployee.setEmployeeId(1L);
		newEmployee.setFirstName("NomeTeste");
		newEmployee.setLastName("SobrenomeTeste");
		newEmployee.setEmail("teste@teste.com");
		
		empRepo.save(newEmployee);
		
		List<Employee> employees = empRepo.findAll();
		
		Ip ip = new Ip();
		ip.setIpId(1L);
		ip.setQuery("23.23.23.23");
		ip.setEmployees(employees);
		ipServRepo.updateWithEmployee(1L, ip);
		
		assertThat(ipServRepo.findAll().equals(employees));
	}
	
	@Test 
	public void ifDeleteIp_thenSuccess() {
		Ip ip = new Ip();
		ip.setIpId(1L);
		ipServRepo.deleteIp(ip);
		assertEquals(0, ipServRepo.findAll().size());
	}
	
}

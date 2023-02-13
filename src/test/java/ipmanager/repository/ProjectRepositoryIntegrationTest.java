package ipmanager.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ipmanager.model.Employee;
import ipmanager.model.Ip;
import ipmanager.service.impl.IpServiceImpl;

@SpringBootTest
public class ProjectRepositoryIntegrationTest {
	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	IpRepository ipRepo;

	@Test
	public void ifNewEmployeeSaved_thenSucess() {
		Employee newEmployee = new Employee();
		newEmployee.setEmployeeId(1L);
		newEmployee.setFirstName("NomeTeste");
		newEmployee.setLastName("SobrenomeTeste");
		newEmployee.setEmail("teste@teste.com");

		empRepo.save(newEmployee);

		assertEquals(1, empRepo.findAll().size());
	}

	@Test
	public void ifUpdateEmployee_thenSuccess() {
		Employee newEmployee = new Employee();
		newEmployee.setEmployeeId(1L);
		newEmployee.setFirstName("NovoNome");
		empRepo.save(newEmployee);

		assertEquals("NovoNome", empRepo.findById(1l).get().getFirstName());
	}

	@Test
	public void ifEmployeeDeleted_thenSucess() {
		Employee newEmployee = new Employee();
		newEmployee.setEmployeeId(1l);
		newEmployee.setFirstName("NomeTeste");
		empRepo.delete(newEmployee);
		assertEquals(0, empRepo.findAll().size());
	}

	@Test
	public void ifNewIpSaved_thenSucess() {
		Ip newIp = new Ip();
		newIp.setIpId(1L);
		newIp.setQuery("24.23.23.24");

		ipRepo.save(newIp);

		assertEquals("24.23.23.24", ipRepo.findById(1L).get().getQuery());
	}

	@Test
	public void ifUpdateIp_thenSuccess() {

		Ip newIp = new Ip();
		newIp.setIpId(1L);
		newIp.setStage("PRODUCTION");
//		List<Employee> employees = empRepo.findAll();
//		newIp.setEmployees(employees);
		ipRepo.save(newIp);

		assertEquals("PRODUCTION", ipRepo.findById(1L).get().getStage());
	}

	@Test
	public void ifIpDeleted_thenSucess() {
		Ip newIp = new Ip();
		newIp.setIpId(1L);

		ipRepo.delete(newIp);;

		assertEquals(0, ipRepo.findAll().size());
	}

}

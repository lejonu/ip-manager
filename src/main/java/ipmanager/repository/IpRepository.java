package ipmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ipmanager.dto.ChartData;
import ipmanager.model.Employee;
import ipmanager.model.Ip;

public interface IpRepository extends CrudRepository<Ip, Long> {
	@Override
	public List<Ip> findAll();

	@Query(nativeQuery = true, value = "SELECT stage AS label, COUNT(*) AS total FROM ip GROUP BY stage")
	public List<ChartData> getStageCount();
	
	@Query(nativeQuery = true, value = "select * from employee as emp\n"
			+ "join ip_employee as ipEmp\n"
			+ "on emp.employee_id = ipEmp.employee_id")
	public List<Employee> getEmployeesIps();
}

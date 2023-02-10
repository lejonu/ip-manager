package ipmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ipmanager.dto.EmployeeIp;
import ipmanager.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	@Override
	public List<Employee> findAll();
	
	@Query(nativeQuery=true, value="SELECT e.first_name AS firstName, e.last_name AS lastName, COUNT(ip.employee_id) AS ipCount\n"
			+ "FROM employee e left join ip_employee ip\n"
			+ "ON ip.employee_id = e.employee_id \n"
			+ "GROUP BY e.first_name, e.last_name\n"
			+ "ORDER BY 3 DESC")
	public List<EmployeeIp> employeeIps();
}

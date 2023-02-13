package ipmanager.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private HomeController controller;

	@Autowired
	private IpController ipController;
	
	@Autowired
	private EmployeeController employeeController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertThat(ipController).isNotNull();
		assertThat(employeeController).isNotNull();
	}
	
	@Test
	public void homePageReturnsVersionNumberCorrectly_thenSuccess() throws Exception  {
		String renderedHtml = this.restTemplate.getForObject("http://localhost:" + port + "/", String.class);

		assertEquals(renderedHtml.contains("1.0.0"), true);
	}
	
	
	@Test
	public void employeesReturnList_thenSuccess() throws Exception  {
		String renderedHtml = this.restTemplate.getForObject("http://localhost:" + port + "/employees", String.class);

		assertEquals(renderedHtml.contains("Email"), true);
	}
	
	
	@Test
	public void ipsReturnList_thenSuccess() throws Exception  {
		String renderedHtml = this.restTemplate.getForObject("http://localhost:" + port + "/ips", String.class);

		assertEquals(renderedHtml.contains("Query"), true);
	}
}

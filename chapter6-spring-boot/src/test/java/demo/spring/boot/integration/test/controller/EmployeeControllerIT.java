package demo.spring.boot.integration.test.controller;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import demo.spring.boot.dao.entity.Employee;
import demo.spring.boot.service.model.EmployeeContext;
import demo.spring.boot.service.model.EmployeeProfile;

@RunWith(SpringRunner.class)
// Web service integration test usage with random port! You have to set this setting!
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({ "classpath:application.properties" })
public class EmployeeControllerIT {

	@Autowired
    private TestRestTemplate restTemplate;
	
	
	@LocalServerPort
	private int tomcatPortNo;
	
	
	
	@Test
	@Order(1)
	public void testRestTemplate() {
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://www.google.com", String.class);
		
		System.out.println(tomcatPortNo);
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertTrue(response.getBody().length() > 0);
	}
	
	@Test
	@Order(2)
	public void findEmployeeById() {
		
		String url = prepareEmployeeRestServiceRootUrl() + "employee/10003";
		
		ResponseEntity<Employee> response = restTemplate.getForEntity(url, Employee.class);
		
		Employee emp = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertTrue(10003 == emp.getEmpNo());
	}
	
	@Test
	@Order(3)
	public void saveEmployee() {
		
		String url = prepareEmployeeRestServiceRootUrl() + "employee";
		
		EmployeeContext employeeContext = new EmployeeContext();
		employeeContext.setName("TestUser1");
		employeeContext.setLastName("testUser1");
		employeeContext.setGender("F");
		employeeContext.setBirthDate(new Date());
		employeeContext.setHireDate(new Date());
		
		ResponseEntity<Long> response = restTemplate.postForEntity(url, employeeContext, Long.class);
		
		Long empNo = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertNotNull(empNo);
	}
	
	@Test
	@Order(4)
	public void getAllEmployeeProfileList() {
		
		String url = prepareEmployeeRestServiceRootUrl() + "employee/profile/list?size=1";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("x-api-key", "BATUHAN");
		
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(httpHeaders);
		
		ResponseEntity<List<EmployeeProfile>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				httpEntity, 
				new ParameterizedTypeReference<List<EmployeeProfile>>() {} );
		
		List<EmployeeProfile> profiles = response.getBody();
		
		Assert.assertTrue(HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
		Assert.assertNotNull(profiles);
		Assert.assertEquals(1, profiles.size());
	}
	
	private String prepareEmployeeRestServiceRootUrl() {
		
		return "http://localhost:" + tomcatPortNo + "/company/";
	}
}

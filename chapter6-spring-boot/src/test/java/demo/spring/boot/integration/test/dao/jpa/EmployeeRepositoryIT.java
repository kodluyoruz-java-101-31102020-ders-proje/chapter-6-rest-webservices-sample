package demo.spring.boot.integration.test.dao.jpa;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import demo.spring.boot.dao.entity.Employee;
import demo.spring.boot.dao.jpa.repository.EmployeeRepository;
import demo.spring.boot.service.model.EmployeeProfile;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource({ "classpath:application.properties" })
public class EmployeeRepositoryIT {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Test
	@Order(1)
	public void selectEmployeeByEmpNo() {
		
		Long maxId = employeeRepository.findMaxId();
		Employee employee = employeeRepository.findWithEmpNo(maxId);
		
		Assert.assertNotNull(employee);
		Assert.assertTrue(employee.getEmpNo() > 0);
	}
	
	@Test
	@Order(2)
	public void selectAllEmployeeProfileList() {
		
		List<EmployeeProfile> employeeProfileList = employeeRepository.getAllEmployeeProfileList(PageRequest.of(0, 1));
		
		Assert.assertEquals(employeeProfileList.size(), 1);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	@Order(3)
	public void saveEmployee() {
		
		Long maxId = employeeRepository.findMaxId();
		Long newEmployeeId = maxId + 1;
		
		Employee employee = new Employee();
		employee.setEmpNo(newEmployeeId);
		employee.setName("Ahmet");
		employee.setLastName("Mehmet");
		employee.setGender("M");
		employee.setBirthDate(new Date());
		employee.setHireDate(new Date());
		
		employeeRepository.save(employee);
		
		employee = employeeRepository.findWithEmpNo(newEmployeeId);
		
		Assert.assertNotNull(employee);
		Assert.assertTrue(employee.getEmpNo() > 0);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Order(4)
	public void updateEmployee() {
		
		Long maxId = employeeRepository.findMaxId();
		Employee employee = employeeRepository.findWithEmpNo(maxId);
		
		employee.setName("Ayşe");
		employee.setLastName("Hürel");
		employee.setGender("M");
		
		employeeRepository.save(employee);
		
		employee = employeeRepository.findWithEmpNo(maxId);
		
		Assert.assertEquals("Ayşe", employee.getName());
		Assert.assertEquals("Hürel", employee.getLastName());
		Assert.assertEquals("M", employee.getGender());
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Order(5)
	public void deleteEmployee() {
		
		Long maxId = employeeRepository.findMaxId();
		Employee employee = employeeRepository.findWithEmpNo(maxId);
		
		employeeRepository.delete(employee);
		
		employee = employeeRepository.findWithEmpNo(maxId);
		
		Assert.assertNull(employee);
	}
}

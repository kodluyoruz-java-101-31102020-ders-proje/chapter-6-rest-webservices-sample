package demo.spring.boot.mock.test.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import demo.spring.boot.dao.entity.Employee;
import demo.spring.boot.dao.jpa.repository.EmployeeRepository;
import demo.spring.boot.dao.mongo.entity.EmployeeDraft;
import demo.spring.boot.dao.mongo.repository.EmployeeDraftRepository;
import demo.spring.boot.service.EmployeeService;
import demo.spring.boot.service.model.EmployeeContext;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceMT {

	@Mock
    private EmployeeRepository employeeRepository;
	
	@Mock
	private EmployeeDraftRepository employeeDraftRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void queryEmployee() {
		
		Employee emp = new Employee();
	    emp.setEmpNo(10L);
	    emp.setName("Mehmet");
	    emp.setLastName("Filiz");
	    emp.setGender("M");
	    emp.setBirthDate(new Date());
	    emp.setHireDate(new Date());
	    
	    Employee emp3 = new Employee();
	    emp3.setEmpNo(11L);
	    emp3.setName("Ayşe");
	    emp3.setLastName("Sümbül");
	    emp3.setGender("M");
	    emp3.setBirthDate(new Date());
	    emp3.setHireDate(new Date());
		 
	    Mockito
	    	.when(employeeRepository.findWithEmpNo(10L))
	    	.thenReturn(emp);
	    
	    Mockito
	    	.when(employeeRepository.findWithEmpNo(11L))
	    	.thenReturn(emp3);
	    
		
		Employee emp2 = employeeService.findByEmpNo(11L);
		
		Assert.assertNotNull(emp2);
		Assert.assertTrue(emp2.getEmpNo() > 0);
	}
	
	@Test
	public void queryAllEmployees() {
		
		Employee emp1 = new Employee();
	    emp1.setEmpNo(10L);
	    emp1.setName("Mehmet");
	    emp1.setLastName("Filiz");
	    emp1.setGender("M");
	    emp1.setBirthDate(new Date());
	    emp1.setHireDate(new Date());
	    
	    Employee emp2 = new Employee();
	    emp2.setEmpNo(10L);
	    emp2.setName("Mehmet");
	    emp2.setLastName("Filiz");
	    emp2.setGender("M");
	    emp2.setBirthDate(new Date());
	    emp2.setHireDate(new Date());
	    
	    
		List<Employee> employees = Arrays.asList(emp1, emp2);
		
		Mockito
			.when(employeeRepository.getAllEmployeeList())
			.thenReturn(employees);
		
		
		
		List<Employee> employees2 = employeeService.getAllEmployeeList();
		
		Assert.assertNotNull(employees2);
		Assert.assertTrue(employees2.size() > 0);
	}
	
	@Test
	public void saveEmployee() {
		
		EmployeeContext empContext = new EmployeeContext();
		empContext.setName("Mehmet");
	    empContext.setLastName("Filiz");
	    empContext.setGender("M");
	    empContext.setBirthDate(new Date());
	    empContext.setHireDate(new Date());
		
	    Long maxEmployeeId = 100L;
	    
		Employee emp = new Employee();
	    emp.setEmpNo(maxEmployeeId + 1);
	    emp.setName(empContext.getName());
	    emp.setLastName(empContext.getLastName());
	    emp.setGender(empContext.getGender());
	    emp.setBirthDate(empContext.getBirthDate());
	    emp.setHireDate(empContext.getHireDate());
		
	    Mockito
			.when(employeeRepository.findMaxId())
			.thenReturn(maxEmployeeId);
	    
		Mockito
			.when(employeeRepository.save(emp))
			.thenReturn(emp);
		
		
		long empId = employeeService.save(empContext);
		
		Assert.assertEquals(101L, empId);
	}
	
	@Test
	public void saveEmployeeAsDraft() {
		
		long empNo = 1008L;
		prepareMockTestRuleEmployeeDraftSave(empNo);
		
		long returnedEmpNo = employeeService.saveAsDraft(empNo);
		
		Assert.assertEquals(empNo, returnedEmpNo);
	}
	
	private void prepareMockTestRuleEmployeeQueryByEmpNo() {
		
		Employee emp = new Employee();
	    emp.setEmpNo(10L);
	    emp.setName("Mehmet");
	    emp.setLastName("Filiz");
	    emp.setGender("M");
	    emp.setBirthDate(new Date());
	    emp.setHireDate(new Date());
		 
	    Mockito
	    	.when(employeeRepository.findWithEmpNo(10L))
	    	.thenReturn(emp);
		
	}
	
	private void prepareMockTestRuleEmployeeProfiles() {
		
		Employee emp1 = new Employee();
	    emp1.setEmpNo(10L);
	    emp1.setName("Mehmet");
	    emp1.setLastName("Filiz");
	    emp1.setGender("M");
	    emp1.setBirthDate(new Date());
	    emp1.setHireDate(new Date());
	    
	    Employee emp2 = new Employee();
	    emp2.setEmpNo(10L);
	    emp2.setName("Mehmet");
	    emp2.setLastName("Filiz");
	    emp2.setGender("M");
	    emp2.setBirthDate(new Date());
	    emp2.setHireDate(new Date());
	    
	    
		List<Employee> employees = Arrays.asList(emp1, emp2);
		
		Mockito
			.when(employeeRepository.getAllEmployeeList())
			.thenReturn(employees);
		
	}
	
	private void prepareMockTestRuleEmployeeSave(EmployeeContext empContext) {
		
		Long employeeId = 100L;
		Employee emp = new Employee();
	    emp.setEmpNo(employeeId + 1);
	    emp.setName(empContext.getName());
	    emp.setLastName(empContext.getLastName());
	    emp.setGender(empContext.getGender());
	    emp.setBirthDate(empContext.getBirthDate());
	    emp.setHireDate(empContext.getHireDate());
		
	    Mockito
			.when(employeeRepository.findMaxId())
			.thenReturn(employeeId);
	    
		Mockito
			.when(employeeRepository.save(emp))
			.thenReturn(emp);
	}
	
	private void prepareMockTestRuleEmployeeDraftSave(Long empNo) {
		
		Employee employee = new Employee();
		employee.setEmpNo(empNo);
		employee.setName("Batuhan");
		employee.setLastName("Düzgün");
		employee.setGender("M");
		employee.setBirthDate(new Date());
		employee.setHireDate(new Date());
		
		Mockito
			.when(employeeRepository.findWithEmpNo(empNo))
			.thenReturn(employee);
		
		EmployeeDraft employeeDraft = new EmployeeDraft();
		Mockito
			.when(employeeDraftRepository.save(employeeDraft))
			.thenReturn(employeeDraft);
	}
}

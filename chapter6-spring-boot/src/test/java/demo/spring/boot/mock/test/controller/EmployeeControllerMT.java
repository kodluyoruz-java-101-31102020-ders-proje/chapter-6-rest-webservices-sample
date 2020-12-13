package demo.spring.boot.mock.test.controller;

import java.util.Arrays;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import demo.spring.boot.controller.EmployeeController;
import demo.spring.boot.service.EmployeeService;
import demo.spring.boot.service.model.EmployeeProfile;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerMT {

	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllEmployeeProfileListWithApiKey() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("x-api-key", "BATUHAN");
        
		
		List<EmployeeProfile> profiles = Arrays.asList(new EmployeeProfile());
		
		Mockito
			.when(employeeService.getAllEmployeeProfileList(1))
			.thenReturn(profiles);
        
        
		ResponseEntity<List<EmployeeProfile>> employeeList = employeeController.getAllEmployeeProfileList(1, request);
	
        Assert.assertTrue(HttpStatus.UNAUTHORIZED.equals(employeeList.getStatusCode()));
        Assert.assertEquals(1, employeeList.getBody().size());
	}
	
	private void prepareMockTestRuleEmployeeProfileList() {
		
		List<EmployeeProfile> profiles = Arrays.asList(new EmployeeProfile());
		
		Mockito
			.when(employeeService.getAllEmployeeProfileList(1))
			.thenReturn(profiles);
	}
	
}

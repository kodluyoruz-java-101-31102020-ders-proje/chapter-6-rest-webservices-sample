package demo.spring.boot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.boot.dao.entity.Employee;
import demo.spring.boot.service.EmployeeService;
import demo.spring.boot.service.model.EmployeeContext;
import demo.spring.boot.service.model.EmployeeProfile;

// Controller Layer (Rest Webservice Endpoints)
@RestController
@RequestMapping("/company")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee findByEmpNo(@PathVariable("id") Long id) {
		
		return employeeService.findByEmpNo(id);
	}
	
	@RequestMapping(value = "/employee/list", method = RequestMethod.GET)
	public List<Employee> getAllEmployeeList() {
		
		return employeeService.getAllEmployeeList();
	}
	
	@RequestMapping(value = "/employee/profile/list", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeProfile>> getAllEmployeeProfileList(
				@RequestParam("size") Integer upperLimit, 
				HttpServletRequest httpRequest) {
		
		String apikey = (String)httpRequest.getHeader("x-api-key");
		
		if(apikey == null) {
			throw new RuntimeException("Set x-api-key error!!!");
		}
		else if(!apikey.equals("BATUHAN")) {
			throw new RuntimeException("Invalid x-api-key error!!!");
		}
		
		List<EmployeeProfile> profiles = employeeService.getAllEmployeeProfileList(upperLimit);
		
		ResponseEntity<List<EmployeeProfile>> response = 
				new ResponseEntity<List<EmployeeProfile>>(profiles, HttpStatus.UNAUTHORIZED);
		
		return response;
	}
	
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public Long save(@RequestBody EmployeeContext employeeContext) {
		
		return employeeService.save(employeeContext);
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
	public Long update(@RequestBody EmployeeContext employeeContext) {
		
		return employeeService.update(employeeContext);
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public Long delete(@PathVariable("id") Long id) {
		
		return employeeService.delete(id);
	}
	
	@RequestMapping(value = "/employee/draft", method = RequestMethod.POST)
	public Long saveAsDraft(@RequestParam("id") Long id) {
		
		return employeeService.saveAsDraft(id);
	}
	
}

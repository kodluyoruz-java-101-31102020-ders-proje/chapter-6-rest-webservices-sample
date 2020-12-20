package demo.spring.boot.web.ui.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.spring.boot.dao.entity.Employee;
import demo.spring.boot.service.EmployeeService;
import demo.spring.boot.service.model.EmployeeContext;


// UI Page Controller
@Controller
@RequestMapping("/pages")
public class PageController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping(value = "/employee/list", method = RequestMethod.GET)
	public String getEmployees(Model model) {
		
		List<Employee> employees = employeeService.getAllEmployeeList();
		model.addAttribute("employees", employees);
		
		return "thyme_employee_list";
	}
	
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String getEmployeeSavePage(EmployeeContext employeeContext) {
		
		return "thyme_employee_save";
	}
	
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String save(EmployeeContext employeeContext, BindingResult result, Model model) {
        
		employeeContext.setBirthDate(new Date());
		employeeContext.setHireDate(new Date());
		
		employeeService.save(employeeContext);
        
		model.addAttribute("employees", employeeService.getAllEmployeeList());
        
        return "thyme_employee_list";
    }
}

package demo.spring.boot.service;

import java.util.List;

import demo.spring.boot.dao.entity.Employee;
import demo.spring.boot.service.model.EmployeeContext;
import demo.spring.boot.service.model.EmployeeProfile;

public interface IEmployeeService {

	public Employee findByEmpNo(Long empNo);
	public List<Employee> getAllEmployeeList();
	public List<EmployeeProfile> getAllEmployeeProfileList(int upperLimit);
	public Long save(EmployeeContext employeeContext);
	public Long update(EmployeeContext employeeContext);
	public Long delete(Long empNo);
	public Long saveAsDraft(Long empNo);
}

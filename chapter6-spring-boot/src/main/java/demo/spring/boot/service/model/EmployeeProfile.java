package demo.spring.boot.service.model;

import demo.spring.boot.dao.entity.Employee;

public class EmployeeProfile {

	private Employee employee;
	private String departmentName;
	
	public EmployeeProfile() { }
	
	public EmployeeProfile(Employee employee, String departmentName) {
		this.employee = employee;
		this.departmentName = departmentName;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}

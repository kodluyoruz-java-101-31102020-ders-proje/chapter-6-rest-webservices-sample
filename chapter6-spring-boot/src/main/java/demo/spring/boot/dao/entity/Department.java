package demo.spring.boot.dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "departments")
public class Department {

	@Id
	@Column(name = "dept_no", columnDefinition = "varchar(255)")
	private String deptNo;
	
	@Column(name = "dept_name")
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY)
	private List<Employee> employees;
	

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

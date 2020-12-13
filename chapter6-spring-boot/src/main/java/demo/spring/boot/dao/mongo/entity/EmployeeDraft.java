package demo.spring.boot.dao.mongo.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees_document")
public class EmployeeDraft implements Serializable{


	private static final long serialVersionUID = -1450263362382412146L;

	@org.springframework.data.annotation.Id
	private BigInteger empNo;
	
	private String name;
	
	private String lastName;
	
	private String gender;
	
	private Date birthDate;
	
	private Date hireDate;
	
	
	public BigInteger getEmpNo() {
		return empNo;
	}

	public void setEmpNo(BigInteger empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
}

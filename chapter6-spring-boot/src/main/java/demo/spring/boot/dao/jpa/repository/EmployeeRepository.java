package demo.spring.boot.dao.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demo.spring.boot.dao.entity.Employee;
import demo.spring.boot.service.model.EmployeeProfile;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Query(value = "SELECT e FROM Employee e WHERE e.empNo = :empNo")
	public Employee findWithEmpNo(@Param("empNo") Long empNo);
	
	@Query(value = "SELECT MAX(e.empNo) FROM Employee e")
	public Long findMaxId();
	
	// SQL sorgusu
	// @Query(value = "SELECT * FROM employees", nativeQuery = true)
	@Query(value = "SELECT e FROM Employee e")
	public List<Employee> getAllEmployeeList();
	
	@Query(value = "SELECT new demo.spring.boot.service.model.EmployeeProfile(emp, empDept.name) FROM Employee emp LEFT OUTER JOIN emp.departments empDept")
	public List<EmployeeProfile> getAllEmployeeProfileList(Pageable pageable);
}

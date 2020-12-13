package demo.spring.boot.dao.mongo.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import demo.spring.boot.dao.mongo.entity.EmployeeDraft;

@Repository
public interface EmployeeDraftRepository extends MongoRepository<EmployeeDraft, BigInteger>{

	@Query(value = "{ 'empNo': ?0 }")
	public EmployeeDraft findEmployeeByEmpNumber(Long id);
}

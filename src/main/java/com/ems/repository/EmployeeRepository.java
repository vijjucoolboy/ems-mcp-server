package com.ems.repository;

import com.ems.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    public Optional<Employee> findByEmpName(String empName);
}

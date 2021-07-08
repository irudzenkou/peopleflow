package com.peopleflow.dao;

import com.peopleflow.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
}

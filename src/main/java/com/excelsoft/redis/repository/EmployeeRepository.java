package com.excelsoft.redis.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelsoft.redis.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}

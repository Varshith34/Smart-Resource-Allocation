package com.javaproject.smartresourceallocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaproject.smartresourceallocation.model.Employee;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findBySkillsAndAvailabilityAndWorkloadLessThan(
	        String skills,
	        boolean availability,
	        int workload
	);
}

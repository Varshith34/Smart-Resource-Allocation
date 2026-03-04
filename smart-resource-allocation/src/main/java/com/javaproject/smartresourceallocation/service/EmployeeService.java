package com.javaproject.smartresourceallocation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaproject.smartresourceallocation.dto.EmployeeDTO;
import com.javaproject.smartresourceallocation.model.Employee;
import com.javaproject.smartresourceallocation.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setSkills(dto.getSkills());
        employee.setExperience(dto.getExperience());
        employee.setAvailability(dto.isAvailability());
        employee.setWorkload(dto.getWorkload());

        Employee saved = employeeRepository.save(employee);

        dto.setId(saved.getId());
        return dto;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(emp -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setId(emp.getId());
                    dto.setName(emp.getName());
                    dto.setSkills(emp.getSkills());
                    dto.setExperience(emp.getExperience());
                    dto.setAvailability(emp.isAvailability());
                    dto.setWorkload(emp.getWorkload());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    public EmployeeDTO allocateEmployee(String skill) {

        List<Employee> candidates = employeeRepository.findAll()
                .stream()
                .filter(emp -> emp.isAvailability())
                .filter(emp -> emp.getSkills() != null &&
                               emp.getSkills().toLowerCase().contains(skill.toLowerCase()))
                .sorted((e1, e2) -> Integer.compare(e1.getWorkload(), e2.getWorkload()))
                .toList();

        if (candidates.isEmpty()) {
            throw new RuntimeException("No available employee with required skill");
        }

        Employee selected = candidates.get(0);

        // Update workload & availability
        selected.setWorkload(selected.getWorkload() + 20);
        if (selected.getWorkload() >= 100) {
            selected.setAvailability(false);
        }

        Employee saved = employeeRepository.save(selected);

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(saved.getId());
        dto.setName(saved.getName());
        dto.setSkills(saved.getSkills());
        dto.setExperience(saved.getExperience());
        dto.setAvailability(saved.isAvailability());
        dto.setWorkload(saved.getWorkload());

        return dto;
    }
}
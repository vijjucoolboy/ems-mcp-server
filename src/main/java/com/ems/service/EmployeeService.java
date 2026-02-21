package com.ems.service;

import com.ems.entity.Employee;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.ems.constant.CommonConstants.EMPLOYEE_NOT_FOUND;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void initializeDb(){

        List<Employee> employees = List.of(
                new Employee(UUID.randomUUID().toString(), "Ritesh Pandey", 25, "Noida", 25000.0, "HR"),
                new Employee(UUID.randomUUID().toString(), "Vijay Kumar", 31, "Bengaluru", 75000.0, "IT"),
                new Employee(UUID.randomUUID().toString(), "Karthik Margam", 35, "Hydrabad", 150000.0, "Finance"),
                new Employee(UUID.randomUUID().toString(), "Rani Mehta", 50, "Mumbai", 105000.0, "IT"),
                new Employee(UUID.randomUUID().toString(), "Jitendra Prasad", 45, "Noida", 45000.0, "Admin")
        );
        employeeRepository.saveAll(employees);

    }

    @Tool(name = "addEmployee", description = "Add a new employee")
    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Tool(name = "updateEmployeeByName", description = "Update an Employee by name if found otherwise throw and exception Employee not found")
    public Employee updateEmployeeByName(@ToolParam(description = "Employee name by which it will search the existing DB and update") String empName,
                                         @ToolParam(description = "New Employee Data") Employee employee){
        Employee savedEmployee = employeeRepository.findByEmpName(empName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(EMPLOYEE_NOT_FOUND, empName)));

        savedEmployee.setDepartment(employee.getDepartment());
        savedEmployee.setSalary(employee.getSalary());
        savedEmployee.setEmpName(employee.getEmpName());
        savedEmployee.setAddress(employee.getAddress());
        savedEmployee.setAge(employee.getAge());

        return employeeRepository.save(savedEmployee);
    }

    @Tool(name = "deleteEmployee", description = "deletes an Employee by name if found otherwise throw and exception Employee not found")
    public void deleteEmployee(String empName) {
        employeeRepository.findById(empName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(EMPLOYEE_NOT_FOUND, empName)));

        employeeRepository.deleteById(empName);
    }

    @Tool(name = "getEmployeeById", description = "Fetch/GET an Employee by name if found otherwise throw and exception Employee not found")
    public Employee getEmployeeById(String empName) {
        return employeeRepository.findById(empName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(EMPLOYEE_NOT_FOUND, empName)));
    }

    @Tool(name = "getAllEmployee", description = "Fetch/GET all Employees")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();

    }
}

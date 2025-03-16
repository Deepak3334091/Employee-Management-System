package com.deepak.ems.service;

import com.deepak.ems.entity.Department;
import com.deepak.ems.entity.Employee;
import com.deepak.ems.entity.Role;
import com.deepak.ems.repository.DepartmentRepository;
import com.deepak.ems.repository.EmployeeRepository;
import com.deepak.ems.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    // Constructor-based dependency injection
    public EmployeeServiceImpl(RoleRepository roleRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    // Save a new employee, ensuring that the roles and department are valid
    @Override
    public Employee saveEmployee(Employee employee) {
        // Ensure employee has at least one role
        if (employee.getRoles() == null || employee.getRoles().isEmpty()) {
            throw new RuntimeException("Employee must have at least one role assigned.");
        }

        Set<Role> assignedRoles = new HashSet<>();
        // Validate each role assigned to the employee
        for (Role role : employee.getRoles()) {
            Role existingRole = roleRepository.findById(role.getId())
                    .orElseThrow(() -> new RuntimeException("Role not found with id " + role.getId()));
            assignedRoles.add(existingRole);
        }
        employee.setRoles(assignedRoles);

        // Ensure employee has a valid department
        if (employee.getDepartment() == null || employee.getDepartment().getId() == null) {
            throw new RuntimeException("Employee must have a valid department assigned.");
        }
        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found with id " + employee.getDepartment().getId()));

        employee.setDepartment(department); // Set the valid department for the employee

        return employeeRepository.save(employee); // Save employee with validated data
    }

    // Retrieve all employees
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Retrieve an employee by ID
    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update an existing employee's details
    public Employee updateEmployee(Long id, Employee newEmployeeData) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Update basic details
        existingEmployee.setFirstName(newEmployeeData.getFirstName());
        existingEmployee.setLastName(newEmployeeData.getLastName());
        existingEmployee.setEmail(newEmployeeData.getEmail());
        existingEmployee.setPhone(newEmployeeData.getPhone());

        // Update roles if provided
        if (newEmployeeData.getRoles() != null && !newEmployeeData.getRoles().isEmpty()) {
            existingEmployee.setRoles(newEmployeeData.getRoles());
        }

        return employeeRepository.save(existingEmployee); // Save updated employee
    }

    // Delete an employee by ID
    @Override
    public void deleteEmployee(Long id) {
        // Check if employee exists before attempting to delete
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id " + id);
        }
        employeeRepository.deleteById(id); // Delete employee from repository
    }
}

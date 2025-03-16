package com.deepak.ems.service;

import com.deepak.ems.entity.Department;
import com.deepak.ems.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Constructor-based dependency injection
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Save a new department
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Retrieve all departments
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Retrieve a department by its ID
    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    // Update an existing department by its ID
    @Override
    public Department updateDepartment(Long id, Department department) {
        // Check if department exists, then update it
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    existingDepartment.setName(department.getName());
                    return departmentRepository.save(existingDepartment);  // Save updated department
                })
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
    }

    // Delete a department by its ID
    @Override
    public void deleteDepartment(Long id) {
        // Check if department exists before deletion
        if (!departmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Department with id " + id + " not found.");
        }
        departmentRepository.deleteById(id);  // Delete department
    }
}

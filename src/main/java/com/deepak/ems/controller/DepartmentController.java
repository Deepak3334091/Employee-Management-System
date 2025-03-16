package com.deepak.ems.controller;

import com.deepak.ems.entity.Department;
import com.deepak.ems.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    // Constructor injection for the DepartmentService
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // GET: Fetch all departments
    @GetMapping("")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // GET: Fetch a department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Add a new department
    @PostMapping("")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    // DELETE: Delete a department by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long id) {
        if (departmentService.getDepartmentById(id).isPresent()) {
            departmentService.deleteDepartment(id);
            return ResponseEntity.ok("Department deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    // PUT: Update an existing department by ID
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
        Optional<Department> updatedDepartment = Optional.ofNullable(departmentService.updateDepartment(id, department));
        return updatedDepartment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

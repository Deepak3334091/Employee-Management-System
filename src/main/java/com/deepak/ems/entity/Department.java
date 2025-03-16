package com.deepak.ems.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")  // Specifies the table name in the database
public class Department {

    @Id  // Primary key annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generated ID
    private Long id;

    private String name;  // Department name

    // One-to-many relationship with Employee entity
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore  // Prevents infinite recursion during serialization
    private List<Employee> employees;  // List of employees in the department

    // Default constructor
    public Department() {}

    // Constructor to initialize Department with values
    public Department(Long id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

package com.deepak.ems.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")  // Specifies the table name in the database
public class Employee {

    @Id  // Primary key annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generated ID
    private Long id;

    @Column(nullable = false)  // Ensures first name is not null
    private String firstName;

    @Column(nullable = false)  // Ensures last name is not null
    private String lastName;

    @Column(nullable = false, unique = true)  // Ensures email is unique and not null
    private String email;

    @Column(nullable = false, unique = true)  // Ensures phone is unique and not null
    private String phone;

    // Many-to-one relationship with Department (each employee belongs to one department)
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)  // Foreign key to Department table
    private Department department;

    // Many-to-many relationship with Role (an employee can have multiple roles)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_roles",  // Join table for the many-to-many relationship
            joinColumns = @JoinColumn(name = "employee_id"),  // Foreign key to Employee
            inverseJoinColumns = @JoinColumn(name = "role_id")  // Foreign key to Role
    )
    private Set<Role> roles;  // Set of roles assigned to the employee

    // Default constructor
    public Employee() {}

    // Constructor to initialize Employee with values
    public Employee(String firstName, String lastName, String email, String phone, Department department, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.roles = roles;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // Override toString method for better string representation of Employee object
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department=" + department +
                ", roles=" + roles +
                '}';
    }
}

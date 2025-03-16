package com.deepak.ems.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")  // Specifies the table name in the database
public class Role {

    @Id  // Primary key annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generated ID
    private Long id;

    @Column(nullable = false, unique = true)  // Ensures the role name is unique and not null
    private String name;

    // Default constructor
    public Role() {}

    // Parameterized constructor to initialize Role with values
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
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

    // Custom toString method for printing role details
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

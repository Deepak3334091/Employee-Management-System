package com.deepak.ems.service;

import com.deepak.ems.entity.Role;
import com.deepak.ems.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    // Constructor-based dependency injection for RoleRepository
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Save a new role after checking if it already exists
    public Role saveRole(Role role) {
        // Check if the role already exists by its name
        Optional<Role> existingRole = roleRepository.findByName(role.getName());
        if (existingRole.isPresent()) {
            throw new RuntimeException("Role with name '" + role.getName() + "' already exists.");
        }
        return roleRepository.save(role); // Save and return the new role
    }

    // Retrieve all roles from the database
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Retrieve a role by its ID
    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Update an existing role's information
    @Override
    public Role updateRole(Long id, Role role) {
        // Find the existing role by ID and update its name
        return roleRepository.findById(id)
                .map(existingRole -> {
                    existingRole.setName(role.getName()); // Update the role name
                    return roleRepository.save(existingRole); // Save and return the updated role
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id)); // Handle case if role not found
    }

    // Delete a role by its ID
    @Override
    public void deleteRole(Long id) {
        // Check if the role exists before attempting to delete
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id " + id);
        }
        roleRepository.deleteById(id); // Delete the role from the repository
    }
}

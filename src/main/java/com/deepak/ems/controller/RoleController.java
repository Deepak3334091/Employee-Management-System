package com.deepak.ems.controller;

import com.deepak.ems.entity.Role;
import com.deepak.ems.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    // Constructor-based dependency injection
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // POST: Add a new Role
    @PostMapping
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        Role savedRole = roleService.saveRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    // GET: Fetch all Roles
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    // GET: Fetch Role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            // Fix the issue by returning a proper response entity (e.g., custom error message)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Role Not Found!"));
        }
    }

    // PUT: Update an existing Role
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @Valid @RequestBody Role role) {
        try {
            Role updatedRole = roleService.updateRole(id, role);
            return ResponseEntity.ok(updatedRole);
        } catch (RuntimeException e) {
            // Fix the issue by returning a proper response entity
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Role Not Found!"));
        }
    }

    // DELETE: Remove a Role by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            // Fix the issue by returning a proper response entity
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Role Not Found!"));
        }
    }

    // Custom error response class
    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

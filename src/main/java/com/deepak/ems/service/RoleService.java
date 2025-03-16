package com.deepak.ems.service;

import com.deepak.ems.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

Role saveRole(Role role);
List<Role> getAllRoles();
Optional<Role> getRoleById(Long id);
Role updateRole(Long id,Role role);
void deleteRole(Long id);

}

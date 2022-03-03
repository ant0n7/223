package com.example.demo.domain.role;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.UUID;

public interface RoleService {
    void addAuthorityToRole( String rolename, String authorityname);
    List<Role> findAll();
    Role saveRole(Role role) throws InstanceAlreadyExistsException;
    Role getRoleById(UUID id);
    Role updateRole(UUID id, Role role) throws InstanceNotFoundException;
    void deleteRole(UUID id) throws InstanceNotFoundException;
}

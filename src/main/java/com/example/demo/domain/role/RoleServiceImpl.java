package com.example.demo.domain.role;

import com.example.demo.domain.authority.Authority;
import com.example.demo.domain.authority.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) throws InstanceAlreadyExistsException {
        if (roleRepository.findByName(role.getName()) != null) {
            throw new InstanceAlreadyExistsException("Role with name \"" + role.getName() + "\" already exists.");
        }
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(UUID id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void addAuthorityToRole( String rolename, String authorityname) {
        Authority authority = authorityRepository.findByName(authorityname);
        Role role = roleRepository.findByName(rolename);
        role.getAuthorities().add(authority);
    }

    @Override
    public Role updateRole(UUID id, Role newRole) throws InstanceNotFoundException {
        if (!roleRepository.existsById(id)) throw new InstanceNotFoundException("Role does not exist.");

        newRole.setId(id);
        return roleRepository.save(newRole);
    }

    public void deleteRole(UUID id) throws InstanceNotFoundException {
        if (!roleRepository.existsById(id)) throw new InstanceNotFoundException("Role does not exist.");
        roleRepository.deleteById(id);
    }
}

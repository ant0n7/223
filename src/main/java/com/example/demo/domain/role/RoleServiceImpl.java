package com.example.demo.domain.role;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.authority.Authority;
import com.example.demo.domain.authority.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

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
        return roleRepository.getById(id);
    }

    @Override
    public void addAuthorityToRole( String rolename, String authorityname) {
        Authority authority = authorityRepository.findByName(authorityname);
        Role role = roleRepository.findByName(rolename);
        role.getAuthorities().add(authority);
    }

    @Override
    public void updateRole(UUID id, Role newRole) throws InstanceNotFoundException {
//        Role role = roleRepository.findById(id);
//        if ((role = roleRepository.getById(id)) == null) {
//            throw new InstanceNotFoundException("Role does not exist.");
//        }
//
//        role = newRole;
//        role.setId(id);
    }

    public void deleteRole(UUID id) throws InstanceNotFoundException {
        roleRepository.deleteById(id);
    }
}

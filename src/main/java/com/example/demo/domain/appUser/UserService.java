package com.example.demo.domain.appUser;


import com.example.demo.domain.appUser.dto.UserSmallDetailsDTO;
import com.example.demo.domain.exceptions.InvalidEmailException;
import com.example.demo.domain.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User saveUser(User user) throws InstanceAlreadyExistsException, InvalidEmailException;
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    User getUser(String username);
    Optional<User> findById(UUID id) throws InstanceNotFoundException;
    List<User> findAll();
    List<UserSmallDetailsDTO> getUsersOfGroup(String groupname, Pageable pageable) throws InstanceNotFoundException;
}

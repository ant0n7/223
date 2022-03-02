package com.example.demo.domain.security;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.group.GroupRepository;
import com.example.demo.domain.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Set;
import java.util.UUID;

@Component
public class SecurityService {
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupRepository groupRepository;
    public boolean isMember(UUID id, String username) throws InstanceAlreadyExistsException, InstanceNotFoundException {
        Set<User> users = groupService.findById(id).orElseThrow(InstanceNotFoundException::new).getUsers();
        for (User u: users) {
            if(u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMember(String groupname, String username) {
        Set<User> users = groupRepository.findByGroupname(groupname).getUsers();
        for (User u: users) {
            if(u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

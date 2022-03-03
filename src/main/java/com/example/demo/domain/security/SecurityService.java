package com.example.demo.domain.security;

import com.example.demo.domain.appuser.User;
import com.example.demo.domain.group.GroupRepository;
import com.example.demo.domain.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Set;
import java.util.UUID;

/**
 * SecurityService - Performs custom authentication validation
 *
 * <ul>
 *     <li>To check if User is accessing own group by groupID</li>
 *     <li>To check if User is accessing own group by groupName</li>
 * </ul>
 *
 * @author Remo Aeberli
 */
@Component
public class SecurityService {
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupRepository groupRepository;

    /**
     * Checks if current user requesting the group is a member of requested group.
     * @param id to find group by UUID
     * @param username to check if username exists in group
     * @return boolean according to result of user being a member of group
     * @throws InstanceAlreadyExistsException
     * @throws InstanceNotFoundException if group not existing
     */
    public boolean isMember(UUID id, String username) throws InstanceAlreadyExistsException, InstanceNotFoundException {
        Set<User> users = groupService.findById(id).orElseThrow(InstanceNotFoundException::new).getUsers();
        for (User u: users) {
            if(u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Same functionality as {@link SecurityService#isMember(UUID, String)} but using groupname instead of UUID
     * @param groupname to find group by groupname
     * @param username to check if username exists in group
     * @return boolean according to result of user being a member of group
     */
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

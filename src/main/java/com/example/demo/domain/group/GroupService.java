package com.example.demo.domain.group;

import com.example.demo.domain.group.dto.MembersOfGroupDTO;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupService {
    List<Group> findAll();
    Group saveGroup(Group group) throws InstanceAlreadyExistsException;
    Optional<Group> findById(UUID id) throws InstanceAlreadyExistsException;
    void deleteGroup(UUID id);
    void updateGroup(UUID id, Group group);
    Group findMembersByGroupname(String groupname);
}

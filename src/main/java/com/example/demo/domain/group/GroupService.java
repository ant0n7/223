package com.example.demo.domain.group;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupService {
    List<Group> findAll();
    Group saveGroup(Group group) throws InstanceAlreadyExistsException;
    Optional<Group> findById(UUID id) throws InstanceAlreadyExistsException, InstanceNotFoundException;
    void deleteGroup(UUID id) throws InstanceNotFoundException;
    Group updateGroup(UUID id, Group group) throws InstanceNotFoundException;
}

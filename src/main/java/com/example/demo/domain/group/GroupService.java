package com.example.demo.domain.group;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface GroupService {
    List<Group> findAll();
    Group saveGroup(Group group) throws InstanceAlreadyExistsException;
}

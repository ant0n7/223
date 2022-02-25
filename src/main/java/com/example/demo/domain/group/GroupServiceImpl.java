package com.example.demo.domain.group;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public List<Group> findAll() {return groupRepository.findAll();}

    @Override
    public Optional<Group> findById(UUID id) {
        return groupRepository.findById(id);
    }


    @Override
    @SneakyThrows
    public Group saveGroup(Group group) {
        if (groupRepository.findByGroupname(group.getGroupname()) != null){
            throw new InstanceAlreadyExistsException("Group already exists");
        }
        else {
            return groupRepository.save(group);
        }
    }

    @Override
    public void updateGroup(UUID id, Group newGroup) {
        newGroup.setId(id);
        groupRepository.save(newGroup);
    }

    @Override
    public void deleteGroup(UUID id) {
        groupRepository.deleteById(id);
    }
}

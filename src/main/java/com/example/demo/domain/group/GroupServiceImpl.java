package com.example.demo.domain.group;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
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
    public Optional<Group> findById(UUID id) throws InstanceNotFoundException {
        UUID groupID = id;
        if(groupRepository.existsById(groupID)) {
            return groupRepository.findById(groupID);
        } else {
            throw new InstanceNotFoundException("Group matching {id:\"" + groupID + "\"} does not exist");
        }
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
    public Group updateGroup(UUID id, Group newGroup) throws InstanceNotFoundException {
        if(groupRepository.existsById(id)) {
            newGroup.setId(id);
            return groupRepository.save(newGroup);
        } else {
            throw new InstanceNotFoundException("Group matching {id:\"" + id + "\"} does not exist");
        }
    }

    @Override
    public void deleteGroup(UUID id) throws InstanceNotFoundException {
        if(groupRepository.existsById(id) && groupRepository.findById(id).isPresent()) {
            groupRepository.deleteById(id);
        } else {
            throw new InstanceNotFoundException("Group matching {id:\"" + id + "\"} does not exist");
        }
    }
}

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

/**
 * GroupService - Service Layer for Group Entity
 *
 * <ul>
 *     <li>To {@link GroupServiceImpl#findAll()} groups </li>
 *     <li>To get group by ID {@link GroupServiceImpl#findById(UUID)}</li>
 *     <li>To {@link GroupServiceImpl#saveGroup(Group)}</li>
 *     <li>To {@link GroupServiceImpl#updateGroup(UUID, Group)}</li>
 *     <li>To {@link GroupServiceImpl#deleteGroup(UUID)}</li>
 * </ul>
 *
 * @author Remo Aeberli
 */
@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    static final String GROUP_MATCHING = "Group matching {id:\"";
    static final String NOT_EXISTING = "\"} does not exist";

    @Override
    public List<Group> findAll() {return groupRepository.findAll();}

    @Override
    public Optional<Group> findById(UUID id) throws InstanceNotFoundException {
        if(groupRepository.existsById(id)) {
            return groupRepository.findById(id);
        } else {
            throw new InstanceNotFoundException(GROUP_MATCHING + id + NOT_EXISTING);
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
            throw new InstanceNotFoundException(GROUP_MATCHING + id + NOT_EXISTING);
        }
    }

    @Override
    public void deleteGroup(UUID id) throws InstanceNotFoundException {
        if(groupRepository.existsById(id) && groupRepository.findById(id).isPresent()) {
            groupRepository.deleteById(id);
        } else {
            throw new InstanceNotFoundException(GROUP_MATCHING + id + NOT_EXISTING);
        }
    }
}

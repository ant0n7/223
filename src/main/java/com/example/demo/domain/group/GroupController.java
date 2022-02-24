package com.example.demo.domain.group;


import com.example.demo.domain.group.dto.MembersOfGroupDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<Collection<Group>> findAll() {
        return new ResponseEntity<Collection<Group>>(groupService.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public Optional<Group> getGroupById(@PathVariable("id") UUID id) {
        return groupService.findById(id);
    }

    @GetMapping("/membersOf/{groupname}")
    public MembersOfGroupDTO getMembersOf(@PathVariable("groupname") String groupname) {
        Group tempgroup = groupService.findMembersByGroupname(groupname);
        return convertToDto(tempgroup);
    }

    @PostMapping("/add")
    @SneakyThrows
    public Group addGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @PutMapping("/update/{id}")
    @SneakyThrows
    public void updateGroup(@PathVariable("id") UUID id, @RequestBody Group group) {
        groupService.updateGroup(id, group);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGroup(@PathVariable("id") UUID id) {
        groupService.deleteGroup(id);
    }

    private MembersOfGroupDTO convertToDto(Group group) {
        MembersOfGroupDTO membersOfGroupDTO = modelMapper.map(group, MembersOfGroupDTO.class);
        membersOfGroupDTO.setId(group.getId());
        membersOfGroupDTO.setGroupname(group.getGroupname());
        membersOfGroupDTO.setMembers(group.getUsers());
        return membersOfGroupDTO;
    }
}

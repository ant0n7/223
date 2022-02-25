package com.example.demo.domain.group;


import com.example.demo.DtoConverter;
import com.example.demo.domain.group.dto.MembersOfGroupDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/")
    public ResponseEntity<Collection<Group>> findAll() {
        return new ResponseEntity<>(groupService.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public Optional<Group> getGroupById(@PathVariable("id") UUID id) {
        return groupService.findById(id);
    }


    @PostMapping("/")
    @SneakyThrows
    public Group addGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @PutMapping("/{id}")
    @SneakyThrows
    public void updateGroup(@PathVariable("id") UUID id, @RequestBody Group group) {
        groupService.updateGroup(id, group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable("id") UUID id) {
        groupService.deleteGroup(id);
    }


}

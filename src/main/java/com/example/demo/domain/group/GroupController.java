package com.example.demo.domain.group;

import com.example.demo.domain.security.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    @Autowired
    private final SecurityService securityService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all Groups", description = "Get a list of all groups with all their information")
    @GetMapping("/")
    public ResponseEntity<Collection<Group>> findAll() {
        return new ResponseEntity<>(groupService.findAll(), HttpStatus.FOUND);
    }

    @PreAuthorize("@securityService.isMember(#id, authentication.principal.username) || hasRole('ADMIN')")
    @Operation(summary = "Get a group by ID.", description = "Receive a single group with all available Information by its UUID.")
    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@Parameter(description = "UUID of the group requested")@PathVariable("id") UUID id) throws InstanceAlreadyExistsException, InstanceNotFoundException {
        return new ResponseEntity<>(groupService.findById(id).orElse(null), HttpStatus.FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Save a single group.", description = "Save a single group to the database.")
    @PostMapping("/")
    public ResponseEntity<Group> addGroup(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Single Group object") @RequestBody @Valid Group group) throws InstanceAlreadyExistsException {
        return new ResponseEntity<>(groupService.saveGroup(group), HttpStatus.CREATED);
    }

    @PreAuthorize("@securityService.isMember(#id, authentication.principal.username) || hasRole('ADMIN')")
    @Operation(summary = "update a group by id.", description = "Updates a single group by sending a requestbody and overwriting old data with new data")
    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@Parameter(description = "UUID of the group requested")@PathVariable("id") UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Single Group object") @RequestBody @Valid Group group) throws InstanceNotFoundException {
        return new ResponseEntity<>(groupService.updateGroup(id, group), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("@securityService.isMember(#id, authentication.principal.username) || hasRole('ADMIN')")
    @Operation(summary = "delete a group by id.", description = "deletes the group matching the id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Group> deleteGroup(@Parameter(description = "finds a group matching the UUID and deletes the group")@PathVariable("id") UUID id) throws InstanceNotFoundException {
        groupService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> handleInstanceNotFoundException(InstanceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> handleInstanceAlreadyExistsException(InstanceAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException() {
        return new ResponseEntity<>("Validation of Entity stops you from doing this!", HttpStatus.BAD_REQUEST);
    }
}
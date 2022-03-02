package com.example.demo.domain.appUser;


import com.example.demo.domain.appUser.dto.UserSmallDetailsDTO;
import com.example.demo.domain.exceptions.InvalidEmailException;
import com.example.demo.domain.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@RestController @RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    //    ADD YOUR ENDPOINT MAPPINGS HERE
    private final UserService userService;

    @Operation(summary = "List of all users")
    @GetMapping("/")
    public ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Persist a single user")
    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User user) throws InstanceAlreadyExistsException, InvalidEmailException {
    public ResponseEntity<User> save(@Valid @RequestBody User user) throws InstanceAlreadyExistsException, InvalidEmailException {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Persist a single role")
    @PostMapping("/role")
    public ResponseEntity<Role> save(@RequestBody Role role) { // TODO(ant0n7): @Valid annotation
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.CREATED);
    }

    @Operation(summary = "Get an user by username")
    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @Operation(summary = "Get all users of a specific group")
    @GetMapping("/groups/{groupname}")
    public ResponseEntity<Collection<UserSmallDetailsDTO>> getUsersOfGroup(@PathVariable String groupname) throws InstanceNotFoundException {
        Pageable pageable = PageRequest.of(0, 3);
        return new ResponseEntity<>(userService.getUsersOfGroup(groupname, pageable), HttpStatus.FOUND);
    }

    @Operation(summary = "Get an user by ID")
    @GetMapping("/byId/{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @Operation(summary = "Add a role to a user")
    @PutMapping("/{username}/addRole/{rolename}")
    public ResponseEntity<String> addRoleToUser(@PathVariable("username") String username, @PathVariable("rolename") String rolename) {
        try {
            userService.addRoleToUser(username, rolename);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> handleInstanceNotFoundException(InstanceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> handleInstanceAlreadyExistsException(InstanceAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(InvalidEmailException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}

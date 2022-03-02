package com.example.demo.domain.appUser;


import com.example.demo.domain.appUser.dto.UserSmallDetailsDTO;
import com.example.demo.domain.exceptions.InvalidEmailException;
import com.example.demo.domain.role.Role;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.naming.Name;
import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@RestController @RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "List of all users.", description = "Get a list of all users with all their information.")
    @GetMapping("/")
    public ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Save a single user.", description = "Save a single user to the database. The API automatically encrypts the password with BCrypt and generates an UUID.")
    @PostMapping("/")
    public ResponseEntity<User> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Single User object") @Valid @RequestBody User user) throws InstanceAlreadyExistsException, InvalidEmailException {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Save a single role.", description = "Save a single role to the database. The API automatically generates an UUID.")
    @PostMapping("/role")
    public ResponseEntity<Role> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Single Role object") @RequestBody Role role) { // TODO(ant0n7): @Valid annotation
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.CREATED);
    }

    @Operation(summary = "Get an user by username.", description = "Receive a single user with all available Information by its username.")
    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@Parameter(name = "Username", description = "Unique username of the user requested") @PathVariable String username) {
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @Operation(summary = "Get all users of a specific group.", description = "Receive all users which are member of the given group in an array. The response may not contain all information about the user.")
    @GetMapping("/groups/{groupname}")
    public ResponseEntity<Collection<UserSmallDetailsDTO>> getUsersOfGroup(@Parameter(description = "Unique name of the group requested")@PathVariable String groupname) throws InstanceNotFoundException {
        Pageable pageable = PageRequest.of(0, 3);
        return new ResponseEntity<>(userService.getUsersOfGroup(groupname, pageable), HttpStatus.FOUND);
    }

    @Operation(summary = "Get an user by ID.", description = "Receive a single user with all available Information by its UUID.")
    @GetMapping("/byId/{id}")
    public ResponseEntity<User> getById(@Parameter(description = "UUID of the user requested") @PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @Operation(summary = "Add a role to a user.", description = "Add a single role to a single user. There won't be any loss of roles as it just adds a role and replaces any roles.")
    @PutMapping("/{username}/addRole/{rolename}")
    public ResponseEntity<String> addRoleToUser(@Parameter(description = "Username of the user") @PathVariable("username") String username, @Parameter(description = "Name of the role which will be added to the user") @PathVariable("rolename") String rolename) {
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

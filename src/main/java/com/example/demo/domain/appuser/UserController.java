package com.example.demo.domain.appuser;


import com.example.demo.domain.appuser.dto.UserSmallDetailsDTO;
import com.example.demo.domain.role.Role;
import com.example.demo.domain.security.SecurityService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserService userService;
    @Autowired
    private SecurityService securityService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "List of all users.", description = "Get a list of all users with all their information.")
    @GetMapping("/")
    public ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Save a single user.", description = "Save a single user to the database. The API automatically encrypts the password with BCrypt and generates an UUID.")
    @PostMapping("/")
    public ResponseEntity<User> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Single User object") @Valid @RequestBody User user)
            throws InstanceAlreadyExistsException {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Save a single role.", description = "Save a single role to the database. The API automatically generates an UUID.")
    @PostMapping("/role")
    public ResponseEntity<Role> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Single Role object") @Valid @RequestBody Role role) {
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.CREATED);
    }

    @PreAuthorize("#username == authentication.principal.username || hasRole('ADMIN')")
    @Operation(summary = "Get an user by username.", description = "Receive a single user with all available Information by its username.")
    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@Parameter(name = "Username", description = "Unique username of the user requested") @PathVariable String username) {
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @PreAuthorize("@securityService.isMember(#groupname, authentication.principal.username) || hasRole('ADMIN')")
    @Operation(summary = "Get all users of a specific group.", description = "Receive all users which are member of the given group in an array. " +
            "The response may not contain all information about the user.")
    @GetMapping("/groups/{groupname}")
    public ResponseEntity<Collection<UserSmallDetailsDTO>> getUsersOfGroup(@Parameter(description = "Unique name of the group requested")
                                                                               @PathVariable String groupname) throws InstanceNotFoundException {
        Pageable pageable = PageRequest.of(0, 3);
        return new ResponseEntity<>(userService.getUsersOfGroup(groupname, pageable), HttpStatus.FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get an user by ID.", description = "Receive a single user with all available Information by its UUID.")
    @GetMapping("/byId/{id}")
    public ResponseEntity<User> getById(@Parameter(description = "UUID of the user requested") @PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<>(userService.findById(id).orElse(null), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add a role to a user.", description = "Add a single role to a single user. There won't be any " +
            "loss of roles as it just adds a role and replaces any roles.")
    @PostMapping("/{username}/role/{rolename}")
    public ResponseEntity<String> addRoleToUser(@Parameter(description = "Username of the user") @PathVariable("username") String username,
                                                @Parameter(description = "Name of the role which will be added to the user") @PathVariable("rolename") String rolename) {
        try {
            userService.addRoleToUser(username, rolename);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a user by ID.", description = "Update a single user by its ID. Pass the whole new user in the " +
            "body and its ID in the path. If there's no user by that ID, nothing will change.")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@Parameter(description = "UUID of the user to change.") @PathVariable UUID id, @Valid @RequestBody User user) throws InstanceNotFoundException {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a user by ID.", description = "Delete a single user by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@Parameter(description = "UUID of the user to delete.") @PathVariable UUID id) throws InstanceNotFoundException {
        userService.deleteUser(id);
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
}

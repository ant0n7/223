package com.example.demo.domain.appUser;


import com.example.demo.domain.role.Role;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Collection;
import java.util.UUID;

@RestController @RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    //    ADD YOUR ENDPOINT MAPPINGS HERE
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<Collection<User>>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User user) throws InstanceAlreadyExistsException {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
    }

    @PostMapping("/role")
    public ResponseEntity<Role> save(@RequestBody Role role) {
        return new ResponseEntity<Role>(userService.saveRole(role), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        return new ResponseEntity<User>(userService.getUser(username), HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<User>(userService.findById(id).get(), HttpStatus.OK);
    }

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
}

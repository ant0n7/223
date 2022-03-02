package com.example.demo.domain.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<Collection<Role>> findAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable UUID id) {
        return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Role> save(@Valid @RequestBody Role role) throws InstanceAlreadyExistsException {
        return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) throws InstanceNotFoundException {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @Valid @RequestBody Role role) throws InstanceNotFoundException {
        roleService.updateRole(id, role);
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

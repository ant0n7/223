package com.example.demo.domain.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "List of all roles.", description = "Get a list of all roles with all their information.")
    @GetMapping("/")
    public ResponseEntity<Collection<Role>> findAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get a role by ID.", description = "Receive a single role with all available Information by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@Parameter(description = "UUID of the role requested.") @PathVariable UUID id) {
        return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Save a single role.", description = "Save a single role to the database. The API automatically " +
            "generates an UUID.")
    @PostMapping("/")
    public ResponseEntity<Role> save(@Valid @RequestBody Role role) throws InstanceAlreadyExistsException {
        return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a role by ID.", description = "Delete a single role by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@Parameter(description = "UUID of the role to delete.") @PathVariable UUID id) throws InstanceNotFoundException {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a role by ID.", description = "Update a single role by its ID. Pass the whole new role in the " +
            "body and its ID in the path. If there's no role by that ID, nothing will change.")
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@Parameter(description = "UUID of the role to change.") @PathVariable UUID id, @Valid @RequestBody Role role) throws InstanceNotFoundException {
        return new ResponseEntity<>(roleService.updateRole(id, role), HttpStatus.OK);
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

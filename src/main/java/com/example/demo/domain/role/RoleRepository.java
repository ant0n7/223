package com.example.demo.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
    void deleteByName(String aDefault);

    @Override
    Optional<Role> findById(UUID uuid);
}
package com.example.demo.domain.appUser;

import com.example.demo.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;
import java.util.UUID;

@Entity(name="users")
//from lombok
@Getter@Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Size(min = 3, max = 16) @NotNull
    private String username;
    @Email @NotNull
    private String email;
    @Size(min = 8, max = 128) @NotNull
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @NotNull
    private Set<Role> roles;



}

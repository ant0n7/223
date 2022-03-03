package com.example.demo.domain.appuser;

import com.example.demo.domain.role.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;
import java.util.UUID;

@Entity(name="tbl_user")
//from lombok
@Getter@Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
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
            name = "tbl_user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @NotNull
    private Set<Role> roles;



}

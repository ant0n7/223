package com.example.demo.domain.group;

import com.example.demo.domain.appuser.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tbl_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(min = 3, max = 16)
    @NotNull(message = "Groupname is mandatory.")
    private String groupname;

    private String motto;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> users;
}

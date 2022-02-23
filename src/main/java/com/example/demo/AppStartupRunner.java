package com.example.demo;
import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserService;
import com.example.demo.domain.authority.Authority;
import com.example.demo.domain.authority.AuthorityRepository;
import com.example.demo.domain.group.Group;
import com.example.demo.domain.group.GroupRepository;
import com.example.demo.domain.group.GroupService;
import com.example.demo.domain.role.Role;
import com.example.demo.domain.role.RoleRepository;
import com.example.demo.domain.role.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;


@Component
@RequiredArgsConstructor
//ApplicationListener used to run commands after startup
public class AppStartupRunner implements ApplicationRunner {
    @Autowired
    private final UserService userService;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final AuthorityRepository authorityRepository;
    @Autowired
    private final GroupService groupService;
    @Autowired
    private final GroupRepository groupRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        RUN YOUR STARTUP CODE HERE
//        e.g. to add a user or role to the DB (only for testing)

//        Authorities
        Authority read_auth=new Authority(null,"READ");
        authorityRepository.save(read_auth);

//        Roles
        Role default_role = new Role(null, "DEFAULT",Arrays.asList(read_auth));
        roleRepository.save(default_role);

        User default_user = new User(null, "james","james.bond@mi6.com","bond", Set.of(default_role));
        userService.saveUser(default_user);

        Group default_group = new Group(null, "Minecraft", "I love diamonds", null);
        groupService.saveGroup(default_group);

        userService.saveUser(new User(null, "anton", "admin@antondetken.ch", "anton", Set.of(default_role)));
        userService.saveUser(new User(null, "remo", "admin@mail.com", "remo", Set.of(default_role)));
        userService.saveUser(new User(null, "andrin", "admin@mail.com", "andrin", Set.of(default_role)));
        userService.saveUser(new User(null, "creeper123", "admin@mail.com", "minecraft", Set.of(default_role)));

        groupService.saveGroup(new Group(null, "Fortnite", "Ninja", Set.of(default_user)));

        userService.addRoleToUser(default_user.getUsername(), default_role.getName());
        userService.addRoleToUser("anton", default_role.getName());
        userService.addRoleToUser("remo", default_role.getName());
        userService.addRoleToUser("andrin", default_role.getName());
        userService.addRoleToUser("creeper123", default_role.getName());
    }
}


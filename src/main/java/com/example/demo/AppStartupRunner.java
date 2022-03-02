package com.example.demo;
import com.example.demo.domain.appUser.UserService;
import com.example.demo.domain.authority.AuthorityRepository;
import com.example.demo.domain.group.GroupRepository;
import com.example.demo.domain.group.GroupService;
import com.example.demo.domain.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


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
    public void run(ApplicationArguments args)  {}
}


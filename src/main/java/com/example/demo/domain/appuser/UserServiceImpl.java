package com.example.demo.domain.appuser;

import com.example.demo.DtoConverter;
import com.example.demo.domain.appuser.dto.UserSmallDetailsDTO;
import com.example.demo.domain.group.GroupRepository;
import com.example.demo.domain.role.Role;
import com.example.demo.domain.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;

import java.util.*;

@Service @RequiredArgsConstructor @Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;
    private final DtoConverter dtoConverter;
    private final PasswordEncoder passwordEncoder;


    @Override
//    This method is used for security authentication, use caution when changing this
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        else {
//          Construct a valid set of Authorities (needs to implement Granted Authorities)
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
                role.getAuthorities().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getName())));
            });
//            return a spring internal user object that contains authorities and roles
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                    .forEach(authorities::add);
        }
        return authorities;
    }

    @Override
    public User saveUser(User user) throws InstanceAlreadyExistsException {
        if (userRepository.findByUsername(user.getUsername()) != null || userRepository.findByEmail(user.getEmail()) != null){
            throw new InstanceAlreadyExistsException("Username or Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public Optional<User> findById(UUID id) throws InstanceNotFoundException{
        if (userRepository.existsById(id)){
            return userRepository.findById(id);
        }
        else {
            throw new InstanceNotFoundException("User not found");
        }
    }

    @Override
    public List<UserSmallDetailsDTO> getUsersOfGroup(String groupname, Pageable pageable) throws InstanceNotFoundException {
        Set<User> userSet = userRepository.findUsersByGroupname(groupname, pageable).toSet();

        if (!userSet.isEmpty()) {
            List<UserSmallDetailsDTO> users = new ArrayList<>();
            Set<UserSmallDetailsDTO> userSmallSet = dtoConverter.convertUserToMembers(userSet);
            users.addAll(userSmallSet);
            return users;
        } else {
            throw new InstanceNotFoundException("Group {" + groupname + "} does not exist");
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(UUID id, User user) throws InstanceNotFoundException {
        if (!userRepository.existsById(id)) throw new InstanceNotFoundException("User does not exist.");

        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) throws InstanceNotFoundException {
        if (!userRepository.existsById(id)) throw new InstanceNotFoundException("User does not exist.");
        userRepository.deleteById(id);
    }
}

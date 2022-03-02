package com.example.demo;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.dto.UserSmallDetailsDTO;
import com.example.demo.domain.group.Group;
import com.example.demo.domain.group.dto.MembersOfGroupDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class DtoConverter {
    private final ModelMapper modelMapper;

    public Set<MembersOfGroupDTO> convertGroupToMembersOfGroupDto(List<Group> group) {
        Set<MembersOfGroupDTO> membersOfGroupDTOSet = new HashSet<>();
        for (Group g: group) {
            MembersOfGroupDTO membersOfGroupDTO = modelMapper.map(g, MembersOfGroupDTO.class);
            membersOfGroupDTO.setId(g.getId());
            membersOfGroupDTO.setGroupname(g.getGroupname());
            membersOfGroupDTO.setMembers(convertUserToMembers(g.getUsers()));
            membersOfGroupDTOSet.add(membersOfGroupDTO);
        }
        return membersOfGroupDTOSet;
    }

    public Set<UserSmallDetailsDTO> convertUserToMembers(Set<User> user) {
        Set<UserSmallDetailsDTO> userDtoSet = new HashSet<>();
        for (User u: user) {
            UserSmallDetailsDTO userSmallDetailsDTOSet = modelMapper.map(u, UserSmallDetailsDTO.class);
            userDtoSet.add(userSmallDetailsDTOSet);
        }
        return userDtoSet;
    }
}

package com.example.demo;

import com.example.demo.domain.appuser.User;
import com.example.demo.domain.appuser.dto.UserSmallDetailsDTO;
import com.example.demo.domain.group.Group;
import com.example.demo.domain.group.dto.MembersOfGroupDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DtoConverter - handles object mapping using the {@link ModelMapper}
 *
 * <ul>
 *     <li>To Map {@link Group} onto {@link MembersOfGroupDTO}</li>
 *     <li>To Map {@link User} onto {@link UserSmallDetailsDTO}</li>
 * </ul>
 *
 * @author Remo Aeberli
 */
@RequiredArgsConstructor
@Component
public class DtoConverter {
    /**
     * Creates a new ModelMapper object
     */
    private final ModelMapper modelMapper;

    /**
     * Converts Groups into a set of {@link MembersOfGroupDTO}s. Method calls {@link DtoConverter#convertUserToMembers(Set)} since Group DTO is based on another DTO.
     * <p>method is not used because of adjustments in requirements</p>
     * @param group - to convert
     * @return set of {@link MembersOfGroupDTO}
     */
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

    /**
     * Converts a set of users into a set of {@link UserSmallDetailsDTO}s
     * @param user to convert
     * @return set of {@link UserSmallDetailsDTO}
     */
    public Set<UserSmallDetailsDTO> convertUserToMembers(Set<User> user) {
        Set<UserSmallDetailsDTO> userDtoSet = new HashSet<>();
        for (User u: user) {
            UserSmallDetailsDTO userSmallDetailsDTOSet = modelMapper.map(u, UserSmallDetailsDTO.class);
            userDtoSet.add(userSmallDetailsDTOSet);
        }
        return userDtoSet;
    }
}

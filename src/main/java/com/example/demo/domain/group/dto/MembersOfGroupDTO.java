package com.example.demo.domain.group.dto;

import com.example.demo.domain.appuser.dto.UserSmallDetailsDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class MembersOfGroupDTO {
    private UUID id;
    private String groupname;
    private Set<UserSmallDetailsDTO> members;
}

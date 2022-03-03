package com.example.demo.domain.appuser.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserSmallDetailsDTO {
    private UUID id;
    private String username;
}

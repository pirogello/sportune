package com.project.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserUpdateDto {
    private UUID id;
    private String username;
    private String prevUsername;
    private String info;
}

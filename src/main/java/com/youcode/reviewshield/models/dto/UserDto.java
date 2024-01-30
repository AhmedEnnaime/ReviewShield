package com.youcode.reviewshield.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private UUID id;
    @NotBlank(message = "username can't be empty")
    private String username;
    @NotBlank(message = "email can't be empty")
    private String email;
    @NotBlank(message = "password can't be empty")
    private String password;
    private Set<RoleDto> roles;
    private List<ReviewDto> reviews;
}

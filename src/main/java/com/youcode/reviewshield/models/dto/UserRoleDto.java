package com.youcode.reviewshield.models.dto;

import com.youcode.reviewshield.models.entities.Role;
import com.youcode.reviewshield.models.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleDto {

    private UUID id;
    @NotNull(message = "user can't be null")
    private UserDto user;
    @NotNull(message = "role can't be null")
    private Role role;
}

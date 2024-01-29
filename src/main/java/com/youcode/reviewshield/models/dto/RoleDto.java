package com.youcode.reviewshield.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {

    private UUID id;
    @NotBlank(message = "name of the role can't be empty")
    private String name;
}

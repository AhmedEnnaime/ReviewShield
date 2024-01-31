package com.youcode.reviewshield.models.dto;

import com.youcode.reviewshield.models.entities.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class ReviewDto {

    private UUID id;
    @NotBlank(message = "title can't be empty")
    private String title;
    @NotBlank(message = "message can't be empty")
    private String message;
    @NotNull
    @Min(value = 0, message = "the minimum value is 0")
    @Max(value = 0, message = "the maximum value is 10")
    private Integer reactions;
    @NotNull(message = "user can't be null")
    private UserDto user;
}

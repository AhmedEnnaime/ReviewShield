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
    private String title;
    private String message;
    private Integer reactions;
    private Boolean reported;
    private User user;
}

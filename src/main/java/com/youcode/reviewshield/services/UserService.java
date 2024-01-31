package com.youcode.reviewshield.services;

import com.youcode.reviewshield.models.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends ServiceInterface<UserDto, UUID>, UserDetailsService {
    UserDto findByUsername(String username);
    boolean report(UUID reviewID);
}

package com.youcode.reviewshield.services.Impl;

import com.youcode.reviewshield.security.dto.AuthenticationRequest;
import com.youcode.reviewshield.security.dto.AuthenticationResponse;
import com.youcode.reviewshield.services.AuthenticationService;
import com.youcode.reviewshield.services.UserRoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        return null;
    }
}

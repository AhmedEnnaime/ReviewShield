package com.youcode.reviewshield.services;

import com.youcode.reviewshield.security.dto.AuthenticationRequest;
import com.youcode.reviewshield.security.dto.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest authRequest);

}

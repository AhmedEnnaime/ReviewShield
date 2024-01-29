package com.youcode.reviewshield.services.Impl;

import com.youcode.reviewshield.models.dto.UserRoleDto;
import com.youcode.reviewshield.repositories.UserRoleRepository;
import com.youcode.reviewshield.services.RoleService;
import com.youcode.reviewshield.services.UserRoleService;
import com.youcode.reviewshield.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private UserService userService;
    private RoleService roleService;

    @Override
    public UserRoleDto save(UserRoleDto userRoleDto) {
        return null;
    }
}

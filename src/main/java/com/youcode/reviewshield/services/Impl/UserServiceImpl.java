package com.youcode.reviewshield.services.Impl;

import com.youcode.reviewshield.models.dto.UserDto;
import com.youcode.reviewshield.repositories.UserRepository;
import com.youcode.reviewshield.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto update(UUID uuid, UserDto userDto) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public UserDto findByID(UUID uuid) {
        return null;
    }
}

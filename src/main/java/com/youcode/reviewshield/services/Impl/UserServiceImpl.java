package com.youcode.reviewshield.services.Impl;

import com.youcode.reviewshield.models.dto.UserDto;
import com.youcode.reviewshield.models.entities.User;
import com.youcode.reviewshield.repositories.UserRepository;
import com.youcode.reviewshield.services.UserService;
import com.youcode.reviewshield.utils.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public UserDto update(UUID uuid, UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (!userRepository.existsById(uuid))
            throw NotFoundException.getNotFoundException(uuid, "user");
        userDto.setId(uuid);
        Optional.ofNullable(user.getUsername()).ifPresent(userDto::setUsername);
        Optional.ofNullable(user.getEmail()).ifPresent(userDto::setEmail);
        Optional.ofNullable(user.getPassword()).ifPresent(userDto::setPassword);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public void delete(UUID uuid) {
        if (!userRepository.existsById(uuid))
            throw NotFoundException.getNotFoundException(uuid, "user");
        userRepository.deleteById(uuid);
    }

    @Override
    public UserDto findByID(UUID uuid) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> NotFoundException.getNotFoundException(uuid, "user"));
        return modelMapper.map(user, UserDto.class);
    }
}

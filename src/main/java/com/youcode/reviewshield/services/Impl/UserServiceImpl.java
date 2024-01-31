package com.youcode.reviewshield.services.Impl;

import com.youcode.reviewshield.models.dto.UserDto;
import com.youcode.reviewshield.models.entities.User;
import com.youcode.reviewshield.models.entities.UserRole;
import com.youcode.reviewshield.repositories.UserRepository;
import com.youcode.reviewshield.repositories.UserRoleRepository;
import com.youcode.reviewshield.services.UserService;
import com.youcode.reviewshield.utils.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final static String ROLE_PREFIX = "ROLE_";

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        List<UserRole> userRoles = userRoleRepository.findAllByUserId(user.get().getId());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userRoles.forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(), authorities);

    }

    @Override
    public UserDto findByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username).get(), UserDto.class);
    }

    @Override
    public boolean report(UUID reviewID) {
        return false;
    }
}

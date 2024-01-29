package com.youcode.reviewshield.services.Impl;

import com.youcode.reviewshield.models.dto.RoleDto;
import com.youcode.reviewshield.models.entities.Role;
import com.youcode.reviewshield.repositories.RoleRepository;
import com.youcode.reviewshield.services.RoleService;
import com.youcode.reviewshield.utils.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        return modelMapper.map(roleRepository.save(role), RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .toList();
    }

    @Override
    public RoleDto update(UUID uuid, RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        if(!roleRepository.existsById(uuid))
            throw NotFoundException.getNotFoundException(uuid, "role");
        roleDto.setId(uuid);
        Optional.ofNullable(role.getName()).ifPresent(roleDto::setName);
        return modelMapper.map(roleRepository.save(role), RoleDto.class);
    }

    @Override
    public void delete(UUID uuid) {
        if (roleRepository.existsById(uuid)) {
            roleRepository.deleteById(uuid);
        }else {
            throw NotFoundException.getNotFoundException(uuid, "role");
        }
    }

    @Override
    public RoleDto findByID(UUID uuid) {
        Role role = roleRepository.findById(uuid)
                .orElseThrow(() -> NotFoundException.getNotFoundException(uuid, "role"));
        return modelMapper.map(role, RoleDto.class);
    }

}

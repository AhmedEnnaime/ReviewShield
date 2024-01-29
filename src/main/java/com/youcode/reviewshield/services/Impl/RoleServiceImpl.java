package com.youcode.reviewshield.services.Impl;

import com.youcode.reviewshield.models.dto.RoleDto;
import com.youcode.reviewshield.repositories.RoleRepository;
import com.youcode.reviewshield.services.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoleDto save(RoleDto roleDto) {
        return null;
    }

    @Override
    public List<RoleDto> getAll() {
        return null;
    }

    @Override
    public RoleDto update(UUID uuid, RoleDto roleDto) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public RoleDto findByID(UUID uuid) {
        return null;
    }
}

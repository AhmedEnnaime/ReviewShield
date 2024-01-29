package com.youcode.reviewshield.utils;

import com.youcode.reviewshield.exceptions.ResourceNotFoundException;

import java.util.UUID;

public class NotFoundException {

    public static ResourceNotFoundException getNotFoundException(UUID uuid, String entity) {
        return new ResourceNotFoundException(String.format("The %s with id %s is not found", entity, uuid));
    }
}


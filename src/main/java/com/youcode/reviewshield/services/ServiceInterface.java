package com.youcode.reviewshield.services;

import java.util.List;

public interface ServiceInterface<Dto, Identifier> {

    Dto save(final Dto dto);
    List<Dto> getAll();
    Dto update(final Identifier identifier, final Dto dto);
    void delete(final Identifier identifier);
    Dto findByID(final Identifier identifier);

}

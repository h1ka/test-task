package com.mcb.creditfactory.service;

import java.util.Optional;

public interface EntityService<Entity, Dto> {
    boolean approve(Dto dto);

    Entity save(Entity entity);

    Optional<Entity> load(Long id);

    Entity fromDto(Dto dto);

    Dto toDTO(Entity entity);

    Long getId(Entity entity);
}

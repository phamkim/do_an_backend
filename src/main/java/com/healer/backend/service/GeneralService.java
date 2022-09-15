package com.healer.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GeneralService<T> {
    List<T> findAll();

    Optional<T> findById(UUID id);

    T save(T t);

    T update(T t, UUID id);

    void deleteById(UUID id);
}

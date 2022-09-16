package com.healer.backend.service.Interface;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IGeneralService<T> {
    List<T> findAll();

    Optional<T> findById(UUID id);

    T save(T t);

    T update(T t, UUID id);

    void deleteById(UUID id);

}

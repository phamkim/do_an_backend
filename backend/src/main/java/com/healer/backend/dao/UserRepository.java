package com.healer.backend.dao;

import com.healer.backend.dto.UserDto;
import com.healer.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

package com.healer.backend.dao;

import com.healer.backend.dto.OrderDto;
import com.healer.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}

package com.healer.backend.dao;

import com.healer.backend.dto.OrderDetailDto;
import com.healer.backend.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
}

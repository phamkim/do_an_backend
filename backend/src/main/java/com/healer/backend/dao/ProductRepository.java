package com.healer.backend.dao;

import com.healer.backend.dto.ProductDto;
import com.healer.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}

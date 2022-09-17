package com.healer.do_an_backend.dao;

import com.healer.do_an_backend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID> {


}

package com.healer.do_an_backend.dao;


import com.healer.do_an_backend.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, UUID> {

}

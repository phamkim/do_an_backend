package com.healer.backend.service.impl;

import com.healer.backend.dao.OrderRepository;
import com.healer.backend.dto.OrderDto;
import com.healer.backend.entities.Order;
import com.healer.backend.entities.User;
import com.healer.backend.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order,OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDto> findById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(result-> modelMapper.map(result,OrderDto.class));
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderDate(orderDto.getOrderDate());
        order.setPaymentDate(orderDto.getPaymentDate());
        order.setState(orderDto.getState());
        order.setUser(modelMapper.map(orderDto.getUserDto(), User.class));
//        order.setOrderDetails();

        return modelMapper.map(orderRepository.save(order),OrderDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }
}

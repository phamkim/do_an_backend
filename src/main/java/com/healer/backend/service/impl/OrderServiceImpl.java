package com.healer.backend.service.impl;

import com.healer.backend.dao.OrderRepository;
import com.healer.backend.dao.UserRepository;
import com.healer.backend.dto.OrderDto;
import com.healer.backend.entities.Order;
import com.healer.backend.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final UserServiceImpl userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, UserServiceImpl userService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.userService = userService;
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
    public OrderDto update(OrderDto orderDto, UUID id) {
        Order order = orderRepository.findById(id).orElse(new Order());
        setOrder(order,orderDto);
        return modelMapper.map(orderRepository.save(order),OrderDto.class);
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = new Order();
        setOrder(order,orderDto);
        return modelMapper.map(orderRepository.save(order),OrderDto.class);
    }


    public void setOrder(Order order, OrderDto orderDto){
        order.setOrderDate(orderDto.getOrderDate());
        order.setPaymentDate(orderDto.getPaymentDate());
        order.setState(orderDto.getState());
        if (!ObjectUtils.isEmpty(orderDto.getUser())){
            userRepository.findById(orderDto.getUser().getId()).map(user -> {
                userService.setUser(user,orderDto.getUser());
                order.setUser(user);
                return order;
            });
        }

//        order.setOrderDetails();
    }

    @Override
    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }
}

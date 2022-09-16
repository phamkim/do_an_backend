package com.healer.backend.service;

import com.healer.backend.dao.IOrderDetailRepository;
import com.healer.backend.dao.IOrderRepository;
import com.healer.backend.dao.IUserRepository;
import com.healer.backend.dto.OrderDto;
import com.healer.backend.entities.Order;
import com.healer.backend.entities.OrderDetail;
import com.healer.backend.service.Interface.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private ModelMapper modelMapper;

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;


    @Autowired
    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public Optional<OrderDto> findById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(result -> modelMapper.map(result, OrderDto.class));
    }
    @Override
    public OrderDto update(OrderDto orderDto, UUID id) {
        Order order = orderRepository.findById(id).orElse(new Order());
        setOrder(order, orderDto);
        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }
    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = new Order();
        setOrder(order, orderDto);
        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }

    public void setOrder(Order order, OrderDto orderDto) {
        order.setOrderDate(orderDto.getOrderDate());
        order.setPaymentDate(orderDto.getPaymentDate());
        order.setState(orderDto.getState());
        if (!ObjectUtils.isEmpty(orderDto.getUser())) {
            userRepository.findById(orderDto.getUser().getId()).map(user -> {
                order.setUser(user);
                return order;
            });
        }
    }
    @Override
    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }
}

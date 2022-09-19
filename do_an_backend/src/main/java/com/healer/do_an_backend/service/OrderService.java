package com.healer.do_an_backend.service;

import com.healer.do_an_backend.dao.IOrderDetailRepository;
import com.healer.do_an_backend.dao.IOrderRepository;
import com.healer.do_an_backend.dao.IProductRepository;
import com.healer.do_an_backend.dao.IUserRepository;
import com.healer.do_an_backend.dto.OrderDetailDto;
import com.healer.do_an_backend.dto.OrderDto;
import com.healer.do_an_backend.entities.Order;
import com.healer.do_an_backend.entities.OrderDetail;
import com.healer.do_an_backend.service.Interface.IOrderService;
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
    private final IOrderDetailRepository orderDetailRepository;
    private final IProductRepository productRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository, IOrderDetailRepository orderDetailRepository, IProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderDto> findAll() {
//        return orderRepository.findAll()
//                .stream()
//                .map(result -> modelMapper.map(result, OrderDto.class))
//                .collect(Collectors.toList());

        return orderRepository.findAll()
                .stream()
                .map(OrderDto::toDto)
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

        if (!ObjectUtils.isEmpty(orderDto.getOrderDetails())) {
            List<OrderDetailDto> orderDetailDtoList = orderDto.getOrderDetails();
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (OrderDetailDto orderDetailDto : orderDetailDtoList) {
                OrderDetail orderDetail = new OrderDetail();
                setOrderDetail(orderDetail, orderDetailDto, order);
                orderDetails.add(orderDetailRepository.save(orderDetail));
            }
            order.setOrderDetails(orderDetails);
        }

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

    public void setOrderDetail(OrderDetail orderDetail, OrderDetailDto orderDetailDto, Order order) {
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        if (!ObjectUtils.isEmpty(order)) {
            orderRepository.findById(order.getId()).map(order1 -> {
                orderDetail.setOrder(order1);
                return order1;
            });
        }
        if (!ObjectUtils.isEmpty(orderDetailDto.getProduct())) {
            productRepository.findById(orderDetailDto.getProduct().getId()).map(product -> {
                orderDetail.setProduct(product);
                return product;
            });
        }
    }

    @Override
    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }
}

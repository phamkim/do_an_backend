package com.healer.backend.service.impl;

import com.healer.backend.dao.OrderDetailRepository;
import com.healer.backend.dao.OrderRepository;
import com.healer.backend.dao.ProductRepository;
import com.healer.backend.dto.OrderDetailDto;
import com.healer.backend.entities.OrderDetail;
import com.healer.backend.service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private ModelMapper modelMapper;

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final ProductServiceImpl productService;

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository, ProductServiceImpl productService, OrderServiceImpl orderService) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public List<OrderDetailDto> findAll() {
        return orderDetailRepository.findAll()
                .stream()
                .map(orderDetail -> modelMapper.map(orderDetail, OrderDetailDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDetailDto> findById(UUID id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        return orderDetail.map(result -> modelMapper.map(result, OrderDetailDto.class));
    }

    @Override
    public OrderDetailDto update(OrderDetailDto orderDetailDto, UUID id) {
        OrderDetail orderDetail =  orderDetailRepository.findById(id).orElse(new OrderDetail());
        setOrderDetail(orderDetail, orderDetailDto);
        return modelMapper.map(orderDetailRepository.save(orderDetail), OrderDetailDto.class);
    }

    @Override
    public OrderDetailDto save(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = new OrderDetail();
        setOrderDetail(orderDetail, orderDetailDto);
        return modelMapper.map(orderDetailRepository.save(orderDetail), OrderDetailDto.class);
    }

    public  void setOrderDetail(OrderDetail orderDetail, OrderDetailDto orderDetailDto) {
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        if (!ObjectUtils.isEmpty(orderDetailDto.getProduct())) {
            productRepository.findById(orderDetailDto.getProduct().getId()).map(product -> {
                productService.setProduct(product, orderDetailDto.getProduct());
                orderDetail.setProduct(product);
                return product;
            });
        }
        if (!ObjectUtils.isEmpty(orderDetailDto.getOrder())) {
            orderRepository.findById(orderDetailDto.getOrder().getId()).map(order -> {
                orderService.setOrder(order, orderDetailDto.getOrder());
                orderDetail.setOrder(order);
                return order;
            });

        }
    }

    @Override
    public void deleteById(UUID id) {
        orderDetailRepository.deleteById(id);
    }


}

package com.healer.backend.service.impl;

import com.healer.backend.dao.OrderDetailRepository;
import com.healer.backend.dto.OrderDetailDto;
import com.healer.backend.entities.Order;
import com.healer.backend.entities.OrderDetail;
import com.healer.backend.entities.Product;
import com.healer.backend.service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private ModelMapper modelMapper;

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(ModelMapper modelMapper, OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetailDto> findAll() {
        return orderDetailRepository.findAll()
                .stream()
                .map(orderDetail -> modelMapper.map(orderDetail,OrderDetailDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDetailDto> findById(UUID id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        return orderDetail.map(result->modelMapper.map(result,OrderDetailDto.class));
    }

    @Override
    public OrderDetailDto save(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetail.setProduct(modelMapper.map(orderDetailDto.getProductDto(), Product.class));
        orderDetail.setOrder(modelMapper.map(orderDetailDto.getOrderDto(), Order.class));
        return modelMapper.map(orderDetailRepository.save(orderDetail),OrderDetailDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        orderDetailRepository.deleteById(id);
    }


}

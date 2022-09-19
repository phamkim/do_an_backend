package com.healer.do_an_backend.service;

import com.healer.do_an_backend.dao.IOrderDetailRepository;
import com.healer.do_an_backend.dao.IOrderRepository;
import com.healer.do_an_backend.dao.IProductRepository;
import com.healer.do_an_backend.dto.OrderDetailDto;
import com.healer.do_an_backend.entities.Order;
import com.healer.do_an_backend.entities.OrderDetail;
import com.healer.do_an_backend.service.Interface.IOrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private ModelMapper modelMapper;

    private final IOrderDetailRepository orderDetailRepository;
    private final IOrderRepository orderRepository;
    private final IProductRepository productRepository;

    @Autowired
    public OrderDetailService(IOrderDetailRepository orderDetailRepository, IProductRepository productRepository, IOrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderDetailDto> findAll() {
        return orderDetailRepository.findAll()
                .stream()
                .map(result -> modelMapper.map(result, OrderDetailDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDetailDto> findById(UUID id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        return orderDetail.map(result -> modelMapper.map(result, OrderDetailDto.class));
    }

    @Override
    public OrderDetailDto update(OrderDetailDto orderDetailDto, UUID id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElse(new OrderDetail());
        setOrderDetail(orderDetail, orderDetailDto);
        return modelMapper.map(orderDetailRepository.save(orderDetail), OrderDetailDto.class);
    }

    @Override
    public OrderDetailDto save(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = new OrderDetail();
        setOrderDetail(orderDetail, orderDetailDto);
        return modelMapper.map(orderDetailRepository.save(orderDetail), OrderDetailDto.class);
    }

    public void setOrderDetail(OrderDetail orderDetail, OrderDetailDto orderDetailDto) {
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        if (!ObjectUtils.isEmpty(orderDetailDto.getProduct())) {
            productRepository.findById(orderDetailDto.getProduct().getId()).map(product -> {
                orderDetail.setProduct(product);
                return product;
            });
        }
//        if (!ObjectUtils.isEmpty(orderDetailDto.getOrder())) {
//            orderRepository.findById(orderDetailDto.getOrder().getId()).map(order -> {
//                orderDetail.setOrder(order);
//                return order;
//            });
//        }
    }

    @Override
    public void deleteById(UUID id) {
        orderDetailRepository.deleteById(id);
    }


}

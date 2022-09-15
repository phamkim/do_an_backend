package com.healer.backend.controller;

import com.healer.backend.dto.OrderDetailDto;
import com.healer.backend.dto.OrderDto;
import com.healer.backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order_detail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> findAll() {
        return new ResponseEntity<>(orderDetailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDto> findById(@PathVariable UUID id) {
        Optional<OrderDetailDto> orderDetailDto = orderDetailService.findById(id);
        return orderDetailDto.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid OrderDetailDto orderDetailDto) {
        orderDetailDto.setId(null);
        OrderDetailDto result = orderDetailService.save(orderDetailDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid OrderDetailDto orderDetailDto, @PathVariable UUID id) {
        Optional<OrderDetailDto> optionalOrderDto = orderDetailService.findById(id);
        return optionalOrderDto.map(result -> {
            orderDetailService.save(orderDetailDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetailDto> deleteById(@PathVariable UUID id) {
        Optional<OrderDetailDto> orderDetailDto = orderDetailService.findById(id);
        return orderDetailDto.map(result -> {
            orderDetailService.deleteById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}

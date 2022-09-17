package com.healer.do_an_backend.controller;

import com.healer.backend.dto.OrderDetailDto;
import com.healer.backend.service.Interface.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/order_detail")
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(IOrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> findAll() {
        return new ResponseEntity<>(orderDetailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDto> findById(@PathVariable UUID id) {
        Optional<OrderDetailDto> orderDetailDto = orderDetailService.findById(id);
        return orderDetailDto.map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid OrderDetailDto orderDetailDto) {
        OrderDetailDto result = orderDetailService.save(orderDetailDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid OrderDetailDto orderDetailDto, @PathVariable UUID id) {
        Optional<OrderDetailDto> orderDetailDtoOptional = orderDetailService.findById(id);
        return orderDetailDtoOptional.map(e -> {
            OrderDetailDto result = orderDetailService.update(orderDetailDto, id);
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

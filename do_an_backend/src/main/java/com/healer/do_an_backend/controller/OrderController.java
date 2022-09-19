package com.healer.do_an_backend.controller;


import com.healer.do_an_backend.dto.OrderDto;
import com.healer.do_an_backend.entities.ResponseObject;
import com.healer.do_an_backend.service.Interface.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/order")
public class OrderController {
    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        try {
            List<OrderDto> result = orderService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable UUID id) {

        try {
            Optional<OrderDto> result = orderService.findById(id);
            if (result.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no order found", result));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid OrderDto orderDto) {
        try {
            OrderDto result = orderService.save(orderDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody @Valid OrderDto orderDto, @PathVariable UUID id) {
        try {
            Optional<OrderDto> order = orderService.findById(id);
            if (order.isPresent()) {
                OrderDto result = orderService.update(orderDto, id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no order founded", order));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable UUID id) {

        try {
            Optional<OrderDto> order = orderService.findById(id);
            if (order.isPresent()) {
                orderService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", order));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no order founded", order));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

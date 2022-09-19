package com.healer.do_an_backend.controller;

import com.healer.do_an_backend.dto.OrderDetailDto;
import com.healer.do_an_backend.dto.UserDto;
import com.healer.do_an_backend.entities.ResponseObject;
import com.healer.do_an_backend.service.Interface.IOrderDetailService;
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
    public ResponseEntity<ResponseObject> findAll() {
        try {
            List<OrderDetailDto> result = orderDetailService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable UUID id) {
        try {
            Optional<OrderDetailDto> result = orderDetailService.findById(id);
            if (result.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no order detail found", result));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid OrderDetailDto orderDetailDto) {
        try {
            OrderDetailDto result = orderDetailService.save(orderDetailDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody @Valid OrderDetailDto orderDetailDto, @PathVariable UUID id) {
        try {
            Optional<OrderDetailDto> orderDetail = orderDetailService.findById(id);
            if (orderDetail.isPresent()) {
                OrderDetailDto result = orderDetailService.update(orderDetailDto, id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no order detail founded", orderDetail));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable UUID id) {
        try {
            Optional<OrderDetailDto> orderDetail = orderDetailService.findById(id);
            if (orderDetail.isPresent()) {
                orderDetailService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", orderDetail));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no order detail founded", orderDetail));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

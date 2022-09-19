package com.healer.do_an_backend.controller;

import com.healer.do_an_backend.dto.ProductDto;
import com.healer.do_an_backend.entities.ResponseObject;
import com.healer.do_an_backend.service.Interface.IProductService;
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
@RequestMapping("/api/v1/product")
public class ProductController {
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        try {
            List<ProductDto> result = productService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable UUID id) {
        try {
            Optional<ProductDto> result = productService.findById(id);
            if (result.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no product found", result));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid ProductDto productDto) {
        try {
            ProductDto result = productService.save(productDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ProductDto productDto, @PathVariable UUID id) {
        try {
            Optional<ProductDto> product = productService.findById(id);
            if (product.isPresent()) {
                ProductDto result = productService.update(productDto, id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no product founded", product));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable UUID id) {
        try {
            Optional<ProductDto> product = productService.findById(id);
            if (product.isPresent()) {
                productService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", product));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no product founded", product));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

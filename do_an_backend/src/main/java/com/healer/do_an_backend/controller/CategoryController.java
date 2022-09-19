package com.healer.do_an_backend.controller;


import com.healer.do_an_backend.dto.CategoryDto;
import com.healer.do_an_backend.entities.ResponseObject;
import com.healer.do_an_backend.service.Interface.ICategoryService;
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
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        try {
            List<CategoryDto> result = categoryService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable UUID id) {
        try {
            Optional<CategoryDto> result = categoryService.findById(id);
            if (result.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no user found", result));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid CategoryDto categoryDto) {
        try {
            CategoryDto result = categoryService.save(categoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody @Valid CategoryDto categoryDto, @PathVariable UUID id) {

        try {
            Optional<CategoryDto> category = categoryService.findById(id);
            if (category.isPresent()) {
                CategoryDto result = categoryService.update(categoryDto, id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", result));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no user founded", category));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable UUID id) {

        try {
            Optional<CategoryDto> category = categoryService.findById(id);
            if (category.isPresent()) {
                categoryService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "query successfully", category));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "no user founded", category));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.healer.backend.controller;


import com.healer.backend.dto.CategoryDto;
import com.healer.backend.service.Interface.ICategoryService;
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
    public ResponseEntity<List<CategoryDto>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable UUID id) {
        Optional<CategoryDto> categoryDto = categoryService.findById(id);
        return categoryDto.map(result -> {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid CategoryDto categoryDto) {
        CategoryDto result = categoryService.save(categoryDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid CategoryDto categoryDto, @PathVariable UUID id) {
        Optional<CategoryDto> categoryDtoOptional = categoryService.findById(id);
        return categoryDtoOptional.map(e -> {
            CategoryDto result = categoryService.update(categoryDto, id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> deleteById(@PathVariable UUID id) {
        Optional<CategoryDto> categoryDto = categoryService.findById(id);
        return categoryDto.map(result -> {
            categoryService.deleteById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}

package com.healer.backend.service.impl;

import com.healer.backend.dao.CategoryRepository;
import com.healer.backend.dto.CategoryDto;
import com.healer.backend.entities.Category;
import com.healer.backend.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> findById(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);

        return category.map(result -> modelMapper.map(result, CategoryDto.class));
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setTitle(categoryDto.getTitle());
        category.setImage(categoryDto.getImage());
//        category.setProducts();
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }


}

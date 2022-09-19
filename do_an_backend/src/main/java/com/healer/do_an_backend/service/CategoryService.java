package com.healer.do_an_backend.service;

import com.healer.do_an_backend.dao.ICategoryRepository;
import com.healer.do_an_backend.dto.CategoryDto;
import com.healer.do_an_backend.entities.Category;
import com.healer.do_an_backend.service.Interface.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ModelMapper modelMapper;
    private final ICategoryRepository categoryRepository;


    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
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
        setCategory(category, categoryDto);
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    public void setCategory(Category category, CategoryDto categoryDto) {
        category.setName(categoryDto.getName());
        category.setImage(categoryDto.getImage());
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, UUID id) {
        Category category = categoryRepository.findById(id).orElse(new Category());
        setCategory(category, categoryDto);
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }
}

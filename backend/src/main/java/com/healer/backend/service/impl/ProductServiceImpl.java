package com.healer.backend.service.impl;

import com.healer.backend.dao.ProductRepository;
import com.healer.backend.dto.ProductDto;
import com.healer.backend.entities.Category;
import com.healer.backend.entities.Product;
import com.healer.backend.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }



    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> findById(UUID id) {
        Optional<Product> product = productRepository.findById(id);

        return product.map(result -> modelMapper.map(result,ProductDto.class));
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscount(product.getDiscount());
        product.setCategory(modelMapper.map(productDto.getCategoryDto(),Category.class));
        return modelMapper.map(productRepository.save(product),ProductDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

}

package com.healer.backend.service.impl;

import com.healer.backend.dao.CategoryRepository;
import com.healer.backend.dao.ProductRepository;
import com.healer.backend.dto.ProductDto;
import com.healer.backend.entities.Product;
import com.healer.backend.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final CategoryServiceImpl categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryServiceImpl categoryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }


    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> findById(UUID id) {
        Optional<Product> product = productRepository.findById(id);

        return product.map(result -> modelMapper.map(result, ProductDto.class));
    }

    @Override
    public ProductDto update(ProductDto productDto, UUID id) {
            Product product = productRepository.findById(id).orElse(new Product());
            setProduct(product, productDto);
            return modelMapper.map(productRepository.save(product), ProductDto.class);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = new Product();
        setProduct(product, productDto);
        return modelMapper.map(productRepository.save(product), ProductDto.class);
    }

    public  void setProduct(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDiscount(productDto.getDiscount());
        if (!ObjectUtils.isEmpty(productDto.getCategory())) {
            categoryRepository.findById(productDto.getCategory().getId()).map(category -> {
                categoryService.toCategory(category, productDto.getCategory());
                categoryService.update(productDto.getCategory(),productDto.getCategory().getId());
                product.setCategory(category);
                return category;
            });
        }
    }

    @Override
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

}

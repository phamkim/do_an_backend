package com.healer.do_an_backend.service;

import com.healer.do_an_backend.dao.ICategoryRepository;
import com.healer.do_an_backend.dao.IProductRepository;
import com.healer.do_an_backend.dto.ProductDto;
import com.healer.do_an_backend.entities.Product;
import com.healer.do_an_backend.service.CategoryService;
import com.healer.do_an_backend.service.Interface.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ModelMapper modelMapper;
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    @Autowired
    public ProductService(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    public void setProduct(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDiscount(productDto.getDiscount());
        if (!ObjectUtils.isEmpty(productDto.getCategory())) {
            categoryRepository.findById(productDto.getCategory().getId()).map(category -> {
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

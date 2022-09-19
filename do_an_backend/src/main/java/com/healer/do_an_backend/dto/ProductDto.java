package com.healer.do_an_backend.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healer.do_an_backend.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDto {

    private UUID id;

    @NotBlank(message = "name not blank")
    private String name;

    @NotBlank(message = "description not blank")
    private String description;

    @NotBlank(message = "price not blank")
    private float price;

    @NotBlank(message = "quantity not blank")
    private float quantity;

    @NotBlank(message = "discount not blank")
    private float discount;

    @NotEmpty(message = "category not empty")
    private CategoryDto category;

    @NotEmpty(message = "images not empty")
    private String images;


}

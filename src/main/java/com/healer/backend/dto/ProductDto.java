package com.healer.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDto {

    private UUID id;

    @NotBlank(message = "name not blank")
    private String name;

    @NotBlank(message = "image not blank")
    private String image;

    @NotBlank(message = "description not blank")
    private String description;

    @NotBlank(message = "price not blank")
    private float price;

    @NotBlank(message = "quantity not blank") // số lượng sản phẩm
    private float quantity;

    @NotBlank(message = "discount not blank")
    private float discount;

    @NotEmpty(message = "category not empty")
    private CategoryDto category;

}

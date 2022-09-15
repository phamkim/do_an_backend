package com.healer.backend.dto;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.healer.backend.entities.Category;
import com.healer.backend.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {

    private UUID id;

    @NotEmpty(message = "not blank")
    private String title;

    @NotEmpty(message = "not blank")
    private String image;

    private List<ProductDto> productDtoList;



}

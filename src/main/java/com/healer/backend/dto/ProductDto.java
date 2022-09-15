package com.healer.backend.dto;

import com.healer.backend.entities.Category;
import com.healer.backend.entities.OrderDetail;
import com.healer.backend.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDto {

    private UUID id;

    @NotEmpty(message = "not blank")
    private String name;

    @NotEmpty(message = "not blank")
    private String image;

    @NotEmpty(message = "not blank")
    private String description;

    @NotEmpty(message = "not blank")
    private float price;

    @NotEmpty(message = "not blank") // số lượng sản phẩm
    private float quantity;

    @NotEmpty(message = "not blank")
    private float discount;


    private CategoryDto category;


//    private List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();



}

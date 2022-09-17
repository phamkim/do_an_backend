package com.healer.do_an_backend.dto;

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
public class OrderDetailDto {
    private UUID id;

    @NotBlank(message = "quantity not blank")
    private float quantity;

    @NotEmpty(message = "product not empty")
    private ProductDto product;

    @NotEmpty(message = "order not empty")
    private OrderDto order;

}
